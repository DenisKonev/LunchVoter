package ru.topjava.lunchvoter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.model.MenuItem;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class MenuItemRepositoryTest {
    @Mock
    private CrudMenuItemRepository crudMenuItemRepository;
    @Mock
    private CrudMenuRepository crudMenuRepository;
    @InjectMocks
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveNewMenuItem() {
        MenuItem newMenuItem = new MenuItem();
        newMenuItem.setName("Pizza");
        when(crudMenuRepository.getReferenceById(MENU_ID_1)).thenReturn(createMenu1());
        when(crudMenuItemRepository.save(newMenuItem)).thenReturn(newMenuItem);
        MenuItem savedMenuItem = menuItemRepository.save(newMenuItem, MENU_ID_1);
        assertNotNull(savedMenuItem);
        assertEquals("Pizza", savedMenuItem.getName());
        verify(crudMenuRepository, times(1)).getReferenceById(MENU_ID_1);
        verify(crudMenuItemRepository, times(1)).save(newMenuItem);
    }

    @Test
    void deleteMenuItem() {
        when(crudMenuItemRepository.delete(MENU_ITEM_ID_1, MENU_ID_1)).thenReturn(1);
        boolean result = menuItemRepository.delete(MENU_ITEM_ID_1, MENU_ID_1);
        assertTrue(result);
        verify(crudMenuItemRepository, times(1)).delete(MENU_ITEM_ID_1, MENU_ID_1);
    }

    @Test
    void getMenuItemByIdAndMenuId() {
        when(crudMenuItemRepository.findById(MENU_ITEM_ID_1)).thenReturn(Optional.of(createMenuItem1()));
        MenuItem fetchedMenuItem = menuItemRepository.get(MENU_ITEM_ID_1, MENU_ID_1);
        assertNotNull(fetchedMenuItem);
        assertEquals(MENU_ITEM_ID_1, fetchedMenuItem.getId());
        assertEquals(MENU_ID_1, fetchedMenuItem.getMenu().getId());
        verify(crudMenuItemRepository, times(1)).findById(MENU_ITEM_ID_1);
    }

    @Test
    void getAllMenuItemsByMenuId() {
        when(crudMenuItemRepository.getAllByMenuId(MENU_ID_1)).thenReturn(createMenuItems());
        List<MenuItem> fetchedMenuItems = menuItemRepository.getAll(MENU_ID_1);
        assertNotNull(fetchedMenuItems);
        assertEquals(2, fetchedMenuItems.size());
        verify(crudMenuItemRepository, times(1)).getAllByMenuId(MENU_ID_1);
    }
}
