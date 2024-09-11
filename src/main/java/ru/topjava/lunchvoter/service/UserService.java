package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.User;
import ru.topjava.lunchvoter.repository.UserRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import java.util.List;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(Integer id) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.get(id), id);
    }

    public void delete(Integer id) {
        checkNotFoundWithId(repository.get(id) != null, id);
        repository.delete(id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "User must not be null");
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(user), user.getId());
    }

    public User create(User user) {
        Assert.notNull(user, "User must not be null");
        ValidationUtil.checkNew(user);
        return repository.save(user);
    }

    public User findByUsername(String username) {
        Assert.hasText(username, "Username must not be empty");
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.findByUsername(username), -1);
    }
}