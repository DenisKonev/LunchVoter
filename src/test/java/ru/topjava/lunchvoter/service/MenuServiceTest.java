package ru.topjava.lunchvoter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.exception.NotFoundException;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.repository.MenuRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class MenuServiceTest {

    @Mock
    private MenuRepository repository;

    @InjectMocks
    private MenuService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(repository.get(anyInt(), anyInt())).thenReturn(createMenu1());
        Menu result = service.get(MENU_ID_1, RESTAURANT_ID_1);
        assertNotNull(result);
        assertEquals(MENU_ID_1, result.getId());
        verify(repository, times(1)).get(anyInt(), anyInt());
    }

    @Test
    void testGetNotFound() {
        when(repository.get(anyInt(), anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.get(MENU_ID_1, RESTAURANT_ID_1);
        });
        assertEquals("Not found entity with id=" + MENU_ID_1, exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.delete(anyInt(), anyInt())).thenReturn(true);
        assertDoesNotThrow(() -> service.delete(MENU_ID_1, RESTAURANT_ID_1));
        verify(repository, times(1)).delete(anyInt(), anyInt());
    }

    @Test
    void testDeleteNotFound() {
        when(repository.delete(anyInt(), anyInt())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.delete(MENU_ID_1, RESTAURANT_ID_1);
        });
        assertEquals("Not found entity with id=" + MENU_ID_1, exception.getMessage());
    }

    @Test
    void testUpdate() {
        Menu menu = createMenu1();
        when(repository.save(any(Menu.class), anyInt())).thenReturn(menu);
        assertDoesNotThrow(() -> service.update(menu, RESTAURANT_ID_1));
        verify(repository, times(1)).save(any(Menu.class), anyInt());
    }

    @Test
    void testUpdateNullMenu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null, RESTAURANT_ID_1);
        });
        assertEquals("menu must not be null", exception.getMessage());
    }

    @Test
    void testCreate() {
        Menu menu = createMenu1();
        when(repository.save(any(Menu.class), anyInt())).thenReturn(menu);
        Menu result = service.create(menu, RESTAURANT_ID_1);
        assertNotNull(result);
        verify(repository, times(1)).save(any(Menu.class), anyInt());
    }

    @Test
    void testGetWithRestaurant() {
        when(repository.getWithRestaurant(anyInt(), anyInt())).thenReturn(createMenu1());
        Menu result = service.getWithRestaurant(MENU_ID_1, RESTAURANT_ID_1);
        assertNotNull(result);
        assertEquals(MENU_ID_1, result.getId());
        verify(repository, times(1)).getWithRestaurant(anyInt(), anyInt());
    }
}