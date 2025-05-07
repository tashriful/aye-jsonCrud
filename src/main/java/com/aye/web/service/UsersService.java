package com.aye.web.service;

import com.aye.web.model.User;
import com.aye.web.repo.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {


    @Autowired
    private UsersRepo usersRepo;

    private static final String FILE_PATH = "src/main/resources/data/users.json";
    private ObjectMapper objectMapper = new ObjectMapper();


    public List<User> getUsers() throws Exception {
        return usersRepo.findAllasMap().values().stream().collect(Collectors.toList());
    }


    public User getUsersById(Long id) throws Exception {
        return usersRepo.findAllasMap().get(id);
    }

    public User createUsers(User user) {
        try {
            List<User> userList = usersRepo.findAll();
            if (user.getId() == null) {
                user.setId(userList.stream().map(User::getId).max(Long::compareTo).orElse(0L) + 1);
            }
            userList.add(user);
            writeToFile(userList);
            return user;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(List<User> userList) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), userList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
