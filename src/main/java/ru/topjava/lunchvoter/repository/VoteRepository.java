package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Vote;

@Repository
public class VoteRepository extends BaseRepository<Vote, Integer> {

    private final CrudVoteRepository crudVoteRepository;

    public VoteRepository(CrudVoteRepository crudVoteRepository) {
        super(crudVoteRepository);
        this.crudVoteRepository = crudVoteRepository;
    }

    public Vote findByUserId(Integer userId) {
        return crudVoteRepository.findByUserId(userId);
    }
}
