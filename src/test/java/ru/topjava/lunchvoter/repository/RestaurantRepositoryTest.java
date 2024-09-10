package ru.topjava.lunchvoter.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.model.Restaurant;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class RestaurantRepositoryTest {

    @Mock
    private CrudRestaurantRepository crudRestaurantRepository;

    @InjectMocks
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveNewRestaurant() {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("New Restaurant");
        when(crudRestaurantRepository.save(newRestaurant)).thenReturn(newRestaurant);
        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);
        assertNotNull(savedRestaurant);
        assertEquals("New Restaurant", savedRestaurant.getName());
        verify(crudRestaurantRepository, times(1)).save(newRestaurant);
    }

    @Test
    void deleteRestaurant() {
        doNothing().when(crudRestaurantRepository).deleteById(RESTAURANT_ID_1);
        restaurantRepository.delete(RESTAURANT_ID_1);
        verify(crudRestaurantRepository, times(1)).deleteById(RESTAURANT_ID_1);
    }

    @Test
    void getRestaurantById() {
        when(crudRestaurantRepository.findById(RESTAURANT_ID_1)).thenReturn(Optional.of(createRestaurant1()));
        Restaurant fetchedRestaurant = restaurantRepository.get(RESTAURANT_ID_1);
        assertNotNull(fetchedRestaurant);
        assertEquals(RESTAURANT_ID_1, fetchedRestaurant.getId());
        assertEquals("Italian Bistro", fetchedRestaurant.getName());
        verify(crudRestaurantRepository, times(1)).findById(RESTAURANT_ID_1);
    }

    @Test
    void getAllRestaurants() {
        when(crudRestaurantRepository.findAll()).thenReturn(createRestaurants());
        List<Restaurant> fetchedRestaurants = restaurantRepository.getAll();
        assertNotNull(fetchedRestaurants);
        assertEquals(2, fetchedRestaurants.size());
        verify(crudRestaurantRepository, times(1)).findAll();
    }
}
