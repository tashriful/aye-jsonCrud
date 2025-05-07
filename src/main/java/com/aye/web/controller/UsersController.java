package com.aye.web.controller;

import com.aye.web.model.User;
import com.aye.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<User> getAllUsers() throws Exception {
        return usersService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUsersById(@PathVariable("id") Long id) throws Exception {

        return usersService.getUsersById(id);
    }

    @PostMapping
    public User createUsers(@RequestBody User user) throws Exception {
        return usersService.createUsers(user);
    }


}
