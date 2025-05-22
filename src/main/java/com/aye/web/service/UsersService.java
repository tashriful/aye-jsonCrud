package com.aye.web.service;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.exception.CustomExceptions;
import com.aye.web.model.Role;
import com.aye.web.model.User;
import com.aye.web.dto.UserRequestDTO;
import com.aye.web.repo.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UsersService {

    private final UsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final FileStorageProperties fileStorageProperties;

    public UsersService(UsersRepo usersRepo, PasswordEncoder passwordEncoder, ObjectMapper objectMapper, FileStorageProperties fileStorageProperties) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
        this.fileStorageProperties = fileStorageProperties;
    }


    public List<User> getUsers() {
        try {
            return usersRepo.findAll(fileStorageProperties.getFilePath()).get("user").
                    values().
                    stream().
                    map(userObj -> objectMapper.convertValue(userObj, User.class)).
                    toList();
        } catch (CustomExceptions.InternalServerErrorException e) {
            throw new CustomExceptions.InternalServerErrorException("Failed to read users from file - "+e);
        }
    }


    public User getUsersById(Long id) throws CustomExceptions.ResourceNotFoundException {
            Object user = usersRepo.findAll(fileStorageProperties.getFilePath()).get("user").get(String.valueOf(id));
            if (user == null){
                throw new CustomExceptions.ResourceNotFoundException("User not found- id: "+id);
            }
            return objectMapper.convertValue(user, User.class);

    }


    public User createUsers(UserRequestDTO userRequestDTO) {
        try {
            Map<String, Map<String, Object>> fullData = usersRepo.findAll(fileStorageProperties.getFilePath());

            Map<String, Object> userMap = fullData.getOrDefault("user", new HashMap<>());
            Map<String, Object> rolesMap = fullData.getOrDefault("role", new HashMap<>());

            if(userRequestDTO.getRoleId() == null){
                throw new CustomExceptions.BadRequestException("Role Not found in Request");
            }

            boolean userNameExist = userMap.values().stream()
                    .map(obj -> objectMapper.convertValue(obj, User.class))
                    .anyMatch(user -> user.getUserName().equalsIgnoreCase(userRequestDTO.getUserName()));

            if (userNameExist){
                throw new CustomExceptions.BadRequestException("Username already exists: " + userRequestDTO.getUserName());
            }

            if (userRequestDTO.getId() == null) {
                List<Long> prevIds = userMap.keySet().stream().map(Long::valueOf).toList();
                Long nextId = prevIds.stream().max(Long::compareTo).orElse(0L) + 1;
                userRequestDTO.setId(nextId);
            }

            userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            Role userRole = objectMapper.convertValue(rolesMap.get(String.valueOf(userRequestDTO.getRoleId())), Role.class);

            User user = new User();
            Set<Role> roles= new LinkedHashSet<>();
            roles.add(userRole);

            user.setId(userRequestDTO.getId());
            user.setUserName(userRequestDTO.getUserName());
            user.setPassword(userRequestDTO.getPassword());
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setActive(userRequestDTO.getActive());
            user.setRoles(roles);
            user.setStartDates(userRequestDTO.getStartDates());
            user.setEndDates(userRequestDTO.getEndDates());


            userMap.put(user.getId().toString(), user);
            fullData.put("user", userMap);

            writeToFile(fullData);

            return objectMapper.convertValue(userMap.get(user.getId().toString()), User.class);

        } catch (CustomExceptions.InternalServerErrorException e) {
            throw new CustomExceptions.InternalServerErrorException("Failed to create user - "+e);
        }
    }

    private void writeToFile(Map<String, Map<String, Object>> userList) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileStorageProperties.getFilePath()), userList);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write user data to file", e);
        }
    }
}
