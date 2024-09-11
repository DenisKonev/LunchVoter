package ru.topjava.lunchvoter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.exception.NotFoundException;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.repository.VoteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class VoteServiceTest {
    @Mock
    private VoteRepository repository;

    @InjectMocks
    private VoteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(repository.get(anyInt())).thenReturn(createVote1());
        Vote result = service.get(VOTE_ID_1);
        assertNotNull(result);
        assertEquals(VOTE_ID_1, result.getId());
        verify(repository, times(1)).get(anyInt());
    }

    @Test
    void testGetNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.get(VOTE_ID_1);
        });
        assertEquals("Not found entity with id=" + VOTE_ID_1, exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.get(anyInt())).thenReturn(createVote1());
        doNothing().when(repository).delete(anyInt());
        assertDoesNotThrow(() -> service.delete(VOTE_ID_1));
        verify(repository, times(1)).delete(anyInt());
    }

    @Test
    void testDeleteNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.delete(VOTE_ID_1);
        });
        assertEquals("Not found entity with id=" + VOTE_ID_1, exception.getMessage());
    }

    @Test
    void testGetAll() {
        when(repository.getAll()).thenReturn(createVotes());
        List<Vote> results = service.getAll();
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(repository, times(1)).getAll();
    }

    @Test
    void testUpdate() {
        Vote vote = createVote1();
        when(repository.save(any(Vote.class))).thenReturn(vote);
        assertDoesNotThrow(() -> service.update(vote));
        verify(repository, times(1)).save(any(Vote.class));
    }

    @Test
    void testUpdateNullVote() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
        assertEquals("Vote must not be null", exception.getMessage());
    }

    @Test
    void testCreate() {
        Vote vote = createVoteWithoutId();
        when(repository.save(any(Vote.class))).thenReturn(vote);
        Vote result = service.create(vote);
        assertNotNull(result);
        verify(repository, times(1)).save(any(Vote.class));
    }

    @Test
    void testFindByUserId() {
        when(repository.findByUserId(anyInt())).thenReturn(createVote1());
        Vote result = service.findByUserId(USER_ID_1);
        assertNotNull(result);
        assertEquals(VOTE_ID_1, result.getId());
        verify(repository, times(1)).findByUserId(anyInt());
    }

    @Test
    void testFindByUserIdNotFound() {
        when(repository.findByUserId(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.findByUserId(USER_ID_1);
        });
        assertEquals("Not found entity with id=-1", exception.getMessage());
    }
}