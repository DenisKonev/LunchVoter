package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.Restaurant;
import ru.topjava.lunchvoter.repository.RestaurantRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import java.util.List;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(Integer id) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.get(id), id);
    }

    public void delete(Integer id) {
        checkNotFoundWithId(repository.get(id) != null, id);
        repository.delete(id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(restaurant), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        ValidationUtil.checkNew(restaurant);
        return repository.save(restaurant);
    }

    public Restaurant getReferenceById(Integer id) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.getReferenceById(id), id);
    }
}