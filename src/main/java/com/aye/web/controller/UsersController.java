package com.aye.web.controller;

import com.aye.web.dto.UserResponseDTO;
import com.aye.web.model.User;
import com.aye.web.dto.UserRequestDTO;
import com.aye.web.utill.DtoConverter;
import com.aye.web.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    // GET all users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<User> users = usersService.getUsers();
        List<UserResponseDTO> userResponseDTOS = users.stream().map(DtoConverter::convertToDTO).toList();
        return ResponseEntity.ok(userResponseDTOS);

    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        User user = usersService.getUsersById(id);
        return ResponseEntity.ok(DtoConverter.convertToDTO(user));

    }

    // POST create user
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDTO userRequestDTO) {

            User createdUser = usersService.createUsers(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertToDTO(createdUser));

    }




}
