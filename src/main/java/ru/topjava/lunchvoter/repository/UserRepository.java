package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.User;

@Repository
public class UserRepository extends BaseRepository<User, Long> {

    private final CrudUserRepository crudUserRepository;

    public UserRepository(CrudUserRepository crudUserRepository) {
        super(crudUserRepository);
        this.crudUserRepository = crudUserRepository;
    }

    public User findByUsername(String username) {
        return crudUserRepository.findByUsername(username);
    }
}
