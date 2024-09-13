package ru.topjava.lunchvoter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.exception.NotFoundException;
import ru.topjava.lunchvoter.model.MenuItem;
import ru.topjava.lunchvoter.repository.MenuItemRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class MenuItemServiceTest {
    @Mock
    private MenuItemRepository repository;

    @InjectMocks
    private MenuItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(repository.get(anyInt(), anyInt())).thenReturn(createMenuItem1());
        MenuItem result = service.get(MENU_ITEM_ID_1, MENU_ID_1);
        assertNotNull(result);
        assertEquals(MENU_ITEM_ID_1, result.getId());
        verify(repository, times(1)).get(anyInt(), anyInt());
    }

    @Test
    void testGetNotFound() {
        when(repository.get(anyInt(), anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.get(MENU_ITEM_ID_1, MENU_ID_1);
        });
        assertEquals("Not found entity with id=" + MENU_ITEM_ID_1, exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.delete(anyInt(), anyInt())).thenReturn(true);
        assertDoesNotThrow(() -> service.delete(MENU_ITEM_ID_1, MENU_ID_1));
        verify(repository, times(1)).delete(anyInt(), anyInt());
    }

    @Test
    void testDeleteNotFound() {
        when(repository.delete(anyInt(), anyInt())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.delete(MENU_ITEM_ID_1, MENU_ID_1);
        });
        assertEquals("Not found entity with id=" + MENU_ITEM_ID_1, exception.getMessage());
    }

    @Test
    void testGetAll() {
        when(repository.getAll(anyInt())).thenReturn(createMenuItems());
        List<MenuItem> results = service.getAll(MENU_ID_1);
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(repository, times(1)).getAll(anyInt());
    }

    @Test
    void testUpdate() {
        MenuItem menuItem = createMenuItem1();
        when(repository.save(any(MenuItem.class), anyInt())).thenReturn(menuItem);
        assertDoesNotThrow(() -> service.update(menuItem, MENU_ID_1, menuItem.getId()));
        verify(repository, times(1)).save(any(MenuItem.class), anyInt());
    }

    @Test
    void testUpdateNullMenuItem() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null, MENU_ID_1, null);
        });
        assertEquals("menuItem must not be null", exception.getMessage());
    }

    @Test
    void testCreate() {
        MenuItem menuItem = createMenuItem1();
        when(repository.save(any(MenuItem.class), anyInt())).thenReturn(menuItem);
        MenuItem result = service.create(menuItem, MENU_ID_1);
        assertNotNull(result);
        verify(repository, times(1)).save(any(MenuItem.class), anyInt());
    }
}