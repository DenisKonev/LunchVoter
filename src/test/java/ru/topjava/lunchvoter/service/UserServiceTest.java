package ru.topjava.lunchvoter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.exception.NotFoundException;
import ru.topjava.lunchvoter.model.User;
import ru.topjava.lunchvoter.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(repository.get(anyInt())).thenReturn(createUser1());
        User result = service.get(USER_ID_1);
        assertNotNull(result);
        assertEquals(USER_ID_1, result.getId());
        verify(repository, times(1)).get(anyInt());
    }

    @Test
    void testGetNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.get(USER_ID_1);
        });
        assertEquals("Not found entity with id=" + USER_ID_1, exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.get(anyInt())).thenReturn(createUser1());
        doNothing().when(repository).delete(anyInt());
        assertDoesNotThrow(() -> service.delete(USER_ID_1));
        verify(repository, times(1)).delete(anyInt());
    }

    @Test
    void testDeleteNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.delete(USER_ID_1);
        });
        assertEquals("Not found entity with id=" + USER_ID_1, exception.getMessage());
    }

    @Test
    void testGetAll() {
        when(repository.getAll()).thenReturn(createUsers());
        List<User> results = service.getAll();
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(repository, times(1)).getAll();
    }

    @Test
    void testUpdate() {
        User user = createUser1();
        when(repository.save(any(User.class))).thenReturn(user);
        assertDoesNotThrow(() -> service.update(user));
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateNullUser() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
        assertEquals("User must not be null", exception.getMessage());
    }

    @Test
    void testCreate() {
        User user = createUserWithoutId();
        when(repository.save(any(User.class))).thenReturn(user);
        User result = service.create(user);
        assertNotNull(result);
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testFindByUsername() {
        when(repository.findByUsername(anyString())).thenReturn(createUser1());
        User result = service.findByUsername("john_doe");
        assertNotNull(result);
        assertEquals(USER_ID_1, result.getId());
        verify(repository, times(1)).findByUsername(anyString());
    }

    @Test
    void testFindByUsernameNotFound() {
        when(repository.findByUsername(anyString())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.findByUsername("john_doe");
        });
        assertEquals("Not found entity with id=-1", exception.getMessage());
    }
}