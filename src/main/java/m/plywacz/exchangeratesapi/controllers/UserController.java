package m.plywacz.exchangeratesapi.controllers;

import m.plywacz.exchangeratesapi.dto.UserDto;
import m.plywacz.exchangeratesapi.model.User;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import m.plywacz.exchangeratesapi.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
Author: BeGieU
Date: 09.02.2020
*/
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/{userId}")
    public User listUser(@PathVariable Long userId) {
        return userService.listUser(userId);
    }
}
