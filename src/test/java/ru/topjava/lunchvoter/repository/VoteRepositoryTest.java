package ru.topjava.lunchvoter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.model.Vote;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class VoteRepositoryTest {

    @Mock
    private CrudVoteRepository crudVoteRepository;

    @InjectMocks
    private VoteRepository voteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveNewVote() {
        Vote newVote = createVote1();
        when(crudVoteRepository.save(newVote)).thenReturn(newVote);
        Vote savedVote = voteRepository.save(newVote);
        assertNotNull(savedVote);
        assertEquals(newVote.getId(), savedVote.getId());
        assertEquals(newVote.getUser(), savedVote.getUser());
        verify(crudVoteRepository, times(1)).save(newVote);
    }

    @Test
    void deleteVote() {
        doNothing().when(crudVoteRepository).deleteById(VOTE_ID_1);
        voteRepository.delete(VOTE_ID_1);
        verify(crudVoteRepository, times(1)).deleteById(VOTE_ID_1);
    }

    @Test
    void getVoteById() {
        Vote expectedVote = createVote1();
        when(crudVoteRepository.findById(VOTE_ID_1)).thenReturn(Optional.of(expectedVote));
        Vote fetchedVote = voteRepository.get(VOTE_ID_1);
        assertNotNull(fetchedVote);
        assertEquals(expectedVote.getId(), fetchedVote.getId());
        assertEquals(expectedVote.getUser(), fetchedVote.getUser());
        verify(crudVoteRepository, times(1)).findById(VOTE_ID_1);
    }

    @Test
    void getAllVotes() {
        when(crudVoteRepository.findAll()).thenReturn(createVotes());
        List<Vote> fetchedVotes = voteRepository.getAll();
        assertNotNull(fetchedVotes);
        assertEquals(2, fetchedVotes.size());
        verify(crudVoteRepository, times(1)).findAll();
    }

    @Test
    void findByUserId() {
        Vote expectedVote = createVote1();
        when(crudVoteRepository.findByUserId(USER_ID_1)).thenReturn(expectedVote);
        Vote fetchedVote = voteRepository.findByUserId(USER_ID_1);
        assertNotNull(fetchedVote);
        assertEquals(expectedVote.getUser(), fetchedVote.getUser());
        verify(crudVoteRepository, times(1)).findByUserId(USER_ID_1);
    }

}
