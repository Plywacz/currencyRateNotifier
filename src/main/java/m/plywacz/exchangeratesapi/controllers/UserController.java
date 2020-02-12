package m.plywacz.exchangeratesapi.controllers;

import m.plywacz.exchangeratesapi.dto.UserDto;
import m.plywacz.exchangeratesapi.model.User;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import m.plywacz.exchangeratesapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return userService.saveUser(userDto);
    }

    @GetMapping("/{userId}")
    public User listUser(@PathVariable Long userId) {
        return userService.listUser(userId);
    }
}
