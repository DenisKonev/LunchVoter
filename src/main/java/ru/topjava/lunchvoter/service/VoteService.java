package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.repository.VoteRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import java.util.List;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(Integer id) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.get(id), id);
    }

    public void delete(Integer id) {
        checkNotFoundWithId(repository.get(id) != null, id);
        repository.delete(id);
    }

    public List<Vote> getAll() {
        return repository.getAll();
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(vote), vote.getId());
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        ValidationUtil.checkNew(vote);
        return repository.save(vote);
    }

    public Vote findByUserId(Integer userId) {
        Assert.notNull(userId, "User ID must not be null");
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.findByUserId(userId), -1);
    }
}