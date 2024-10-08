package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Vote;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    Vote findByUserId(Integer userId);

    Vote getReferenceById(Integer id);
}