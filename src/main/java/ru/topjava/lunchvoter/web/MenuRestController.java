package ru.topjava.lunchvoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.service.MenuService;

import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/restaurants/{restaurantId}/menus";
    private final MenuService service;

    public MenuRestController(MenuService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int restaurantId, @PathVariable int id) {
        return service.get(id, restaurantId);
    }

    @GetMapping
    public List<Menu> getAll(@PathVariable int restaurantId) {
        return service.getAll(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Menu create(@Validated @RequestBody Menu menu, @PathVariable int restaurantId) {
        return service.create(menu, restaurantId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody Menu menu, @PathVariable int restaurantId, @PathVariable int id) {
        service.update(menu, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        service.delete(id, restaurantId);
    }

    @GetMapping("/{id}/with-restaurant")
    public Menu getWithRestaurant(@PathVariable int restaurantId, @PathVariable int id) {
        return service.getWithRestaurant(id, restaurantId);
    }
}