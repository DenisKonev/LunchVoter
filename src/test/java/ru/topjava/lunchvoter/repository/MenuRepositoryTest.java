package ru.topjava.lunchvoter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.model.Menu;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class MenuRepositoryTest {
    @Mock
    private CrudMenuRepository crudMenuRepository;
    @Mock
    private CrudRestaurantRepository crudRestaurantRepository;
    @InjectMocks
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveNewMenu() {
        Menu newMenu = new Menu();
        when(crudRestaurantRepository.getReferenceById(RESTAURANT_ID_1)).thenReturn(createRestaurant1());
        when(crudMenuRepository.save(newMenu)).thenReturn(newMenu);
        Menu savedMenu = menuRepository.save(newMenu, RESTAURANT_ID_1);
        assertNotNull(savedMenu);
        assertEquals(RESTAURANT_ID_1, savedMenu.getRestaurant().getId());
        verify(crudRestaurantRepository, times(1)).getReferenceById(RESTAURANT_ID_1);
        verify(crudMenuRepository, times(1)).save(newMenu);
    }

    @Test
    void deleteMenu() {
        when(crudMenuRepository.delete(MENU_ID_1, RESTAURANT_ID_1)).thenReturn(1);
        assertTrue(menuRepository.delete(MENU_ID_1, RESTAURANT_ID_1));
        verify(crudMenuRepository, times(1)).delete(MENU_ID_1, RESTAURANT_ID_1);
    }

    @Test
    void getMenuByIdAndRestaurantId() {
        when(crudMenuRepository.findById(MENU_ID_1)).thenReturn(Optional.of(createMenu1()));
        Menu fetchedMenu = menuRepository.get(MENU_ID_1, RESTAURANT_ID_1);
        assertNotNull(fetchedMenu);
        assertEquals(MENU_ID_1, fetchedMenu.getId());
        assertEquals(RESTAURANT_ID_1, fetchedMenu.getRestaurant().getId());
        verify(crudMenuRepository, times(1)).findById(MENU_ID_1);
    }

    @Test
    void getAllMenusByRestaurantId() {
        when(crudMenuRepository.getAllByRestaurantId(RESTAURANT_ID_1)).thenReturn(createMenus());
        List<Menu> fetchedMenus = menuRepository.getAll(RESTAURANT_ID_1);
        assertNotNull(fetchedMenus);
        assertEquals(2, fetchedMenus.size());
        verify(crudMenuRepository, times(1)).getAllByRestaurantId(RESTAURANT_ID_1);
    }

    @Test
    void getWithRestaurant() {
        when(crudMenuRepository.getWithRestaurant(MENU_ID_1, RESTAURANT_ID_1)).thenReturn(createMenu1());
        Menu result = menuRepository.getWithRestaurant(MENU_ID_1, RESTAURANT_ID_1);
        assertNotNull(result);
        assertEquals(MENU_ID_1, result.getId());
        verify(crudMenuRepository, times(1)).getWithRestaurant(MENU_ID_1, RESTAURANT_ID_1);
    }
}