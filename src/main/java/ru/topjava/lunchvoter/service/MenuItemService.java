package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.lunchvoter.model.MenuItem;
import ru.topjava.lunchvoter.repository.MenuItemRepository;
import ru.topjava.lunchvoter.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

import static ru.topjava.lunchvoter.util.ValidationUtil.checkListNotEmpty;
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

    private static void setCurrentLocalDateTime(MenuItem menuItem) {
        menuItem.setCreatedAt(LocalDateTime.now());
    }

    public List<MenuItem> getAll(Integer menuId) {
        return checkListNotEmpty(repository.getAll(menuId), menuId);
    }

    public MenuItem create(MenuItem menuItem, Integer menuId) {
        Assert.notNull(menuItem, "menuItem must not be null");
        ValidationUtil.checkNew(menuItem);
        setCurrentLocalDateTime(menuItem);
        return repository.save(menuItem, menuId);
    }

    public void update(MenuItem menuItem, Integer menuId, Integer menuItemId) {
        Assert.notNull(menuItem, "menuItem must not be null");
        setCurrentLocalDateTime(menuItem);
        menuItem.setId(menuItemId);
        ValidationUtil.checkNotFoundWithIdGeneric(repository.save(menuItem, menuId), menuItem.getId());
    }

    public void delete(Integer id, Integer menuId) {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }
}