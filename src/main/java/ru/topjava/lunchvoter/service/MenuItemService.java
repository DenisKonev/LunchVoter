package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.MenuItem;
import ru.topjava.lunchvoter.repository.MenuItemRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import java.util.List;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem get(Integer id, Integer menuId) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.get(id, menuId), id);
    }

    public void delete(Integer id, Integer menuId) {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }

    public List<MenuItem> getAll(Integer menuId) {
        return repository.getAll(menuId);
    }

    public void update(MenuItem menuItem, Integer menuId) {
        Assert.notNull(menuItem, "menuItem must not be null");
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(menuItem, menuId), menuItem.getId());
    }

    public MenuItem create(MenuItem menuItem, Integer menuId) {
        Assert.notNull(menuItem, "menuItem must not be null");
        return repository.save(menuItem, menuId);
    }

    public MenuItem getWithMenu(Integer id, Integer menuId) {
        return ValidationUtil.checkNotFoundWithIdGeneric(repository.getWithMenu(id, menuId), id);
    }
}