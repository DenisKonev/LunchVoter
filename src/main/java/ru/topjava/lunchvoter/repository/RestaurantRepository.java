package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Restaurant;

@Repository
public class RestaurantRepository extends BaseRepository<Restaurant, Long> {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        super(crudRestaurantRepository);
        this.crudRestaurantRepository = crudRestaurantRepository;
    }
}
