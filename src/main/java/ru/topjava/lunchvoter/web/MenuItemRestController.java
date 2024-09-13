package ru.topjava.lunchvoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.MenuItem;
import ru.topjava.lunchvoter.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping(value = MenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemRestController {
    static final String REST_URL = "/rest/menus/{menuId}/menu-items";
    private final MenuItemService service;

    public MenuItemRestController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable int menuId, @PathVariable int id) {
        return service.get(id, menuId);
    }

    @GetMapping
    public List<MenuItem> getAll(@PathVariable int menuId) {
        return service.getAll(menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem create(@Validated @RequestBody MenuItem menuItem, @PathVariable int menuId) {
        return service.create(menuItem, menuId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody MenuItem menuItem, @PathVariable int menuId, @PathVariable int id) {
        service.update(menuItem, menuId, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int menuId, @PathVariable int id) {
        service.delete(id, menuId);
    }
}