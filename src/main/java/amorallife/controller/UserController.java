package amorallife.controller;

import amorallife.dto.ProductDto;
import amorallife.entity.User;
import amorallife.entity.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import amorallife.dto.UserDto;
import amorallife.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto findByUsername(@PathVariable("name") String name) {
        return userService.findByUsername(name);
    }

    @GetMapping(value = "/role/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRole findRoleById(@PathVariable("id") Long id) {
        return userService.findRoleById(id);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/registration", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto register(@PathVariable("id") User user, UserRole userRole) {
        return userService.register(user, userRole);
    }

    @GetMapping(value = "/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
