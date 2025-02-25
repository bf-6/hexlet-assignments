package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping("{id}")
    public Mono<User> show(@PathVariable long id) {
        return userService.show(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("{id}")
    public Mono<User> update(@RequestBody User user, @PathVariable long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable long id) {
       return userService.destroy(id);
    }
    // END
}
