package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.repository.MenuRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(Integer id, Integer restaurantId) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.get(id, restaurantId), id);
    }

    public void delete(Integer id, Integer restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public void update(Menu menu, Integer restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(menu, restaurantId), menu.getId());
    }

    public Menu create(Menu menu, Integer restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    public Menu getWithRestaurant(Integer id, Integer restaurantId) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.getWithRestaurant(id, restaurantId), id);
    }
}