package ru.topjava.lunchvoter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.topjava.lunchvoter.exception.NotFoundException;
import ru.topjava.lunchvoter.model.Restaurant;
import ru.topjava.lunchvoter.repository.RestaurantRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.topjava.lunchvoter.TestData.*;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        when(repository.get(anyInt())).thenReturn(createRestaurant1());
        Restaurant result = service.get(RESTAURANT_ID_1);
        assertNotNull(result);
        assertEquals(RESTAURANT_ID_1, result.getId());
        verify(repository, times(1)).get(anyInt());
    }

    @Test
    void testGetNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.get(RESTAURANT_ID_1);
        });
        assertEquals("Not found entity with id=" + RESTAURANT_ID_1, exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.get(anyInt())).thenReturn(createRestaurant1());
        doNothing().when(repository).delete(anyInt());
        assertDoesNotThrow(() -> service.delete(RESTAURANT_ID_1));
        verify(repository, times(1)).delete(anyInt());
    }

    @Test
    void testDeleteNotFound() {
        when(repository.get(anyInt())).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.delete(RESTAURANT_ID_1);
        });
        assertEquals("Not found entity with id=" + RESTAURANT_ID_1, exception.getMessage());
    }

    @Test
    void testGetAll() {
        when(repository.getAll()).thenReturn(createRestaurants());
        List<Restaurant> results = service.getAll();
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(repository, times(1)).getAll();
    }

    @Test
    void testUpdate() {
        Restaurant restaurant = createRestaurant1();
        when(repository.save(any(Restaurant.class))).thenReturn(restaurant);
        assertDoesNotThrow(() -> service.update(restaurant));
        verify(repository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void testUpdateNullRestaurant() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
        assertEquals("Restaurant must not be null", exception.getMessage());
    }

    @Test
    void testCreate() {
        Restaurant restaurant = createRestaurantWithoutId();
        when(repository.save(any(Restaurant.class))).thenReturn(restaurant);
        assertNotNull(service.create(restaurant));
        verify(repository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void testGetReferenceById() {
        when(repository.getReferenceById(anyInt())).thenReturn(createRestaurant1());
        Restaurant result = service.getReferenceById(RESTAURANT_ID_1);
        assertNotNull(result);
        assertEquals(RESTAURANT_ID_1, result.getId());
        verify(repository, times(1)).getReferenceById(anyInt());
    }
}