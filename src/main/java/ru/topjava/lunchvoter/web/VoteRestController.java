package ru.topjava.lunchvoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.service.VoteService;

import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/votes";
    private final VoteService service;

    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping
    public List<Vote> getAll() {
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Vote create(@Validated @RequestBody Vote vote) {
        return service.create(vote);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody Vote vote, @PathVariable int id) {
        vote.setId(id);
        service.update(vote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/by-user")
    public Vote findByUserId(@RequestParam int userId) {
        return service.findByUserId(userId);
    }
}