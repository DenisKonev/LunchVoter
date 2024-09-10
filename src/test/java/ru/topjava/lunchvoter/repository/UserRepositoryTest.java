package ru.topjava.lunchvoter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class UserRepositoryTest {

    @Mock
    private CrudUserRepository crudUserRepository;

    @InjectMocks
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveNewUser() {
        User newUser = new User();
        newUser.setUsername("new_user");
        when(crudUserRepository.save(newUser)).thenReturn(newUser);
        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser);
        assertEquals("new_user", savedUser.getUsername());
        verify(crudUserRepository, times(1)).save(newUser);
    }

    @Test
    void deleteUser() {
        doNothing().when(crudUserRepository).deleteById(USER_ID_1);
        userRepository.delete(USER_ID_1);
        verify(crudUserRepository, times(1)).deleteById(USER_ID_1);
    }

    @Test
    void getUserById() {
        when(crudUserRepository.findById(USER_ID_1)).thenReturn(Optional.of(createUser1()));
        User fetchedUser = userRepository.get(USER_ID_1);
        assertNotNull(fetchedUser);
        assertEquals(USER_ID_1, fetchedUser.getId());
        assertEquals("john_doe", fetchedUser.getUsername());
        verify(crudUserRepository, times(1)).findById(USER_ID_1);
    }

    @Test
    void getAllUsers() {
        when(crudUserRepository.findAll()).thenReturn(createUsers());
        List<User> fetchedUsers = userRepository.getAll();
        assertNotNull(fetchedUsers);
        assertEquals(2, fetchedUsers.size());
        verify(crudUserRepository, times(1)).findAll();
    }

    @Test
    void findByUsername() {
        when(crudUserRepository.findByUsername("john_doe")).thenReturn(createUser1());
        User fetchedUser = userRepository.findByUsername("john_doe");
        assertNotNull(fetchedUser);
        assertEquals("john_doe", fetchedUser.getUsername());
        verify(crudUserRepository, times(1)).findByUsername("john_doe");
    }
}