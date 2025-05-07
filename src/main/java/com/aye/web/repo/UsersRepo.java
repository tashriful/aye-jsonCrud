package com.aye.web.repo;

import com.aye.web.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UsersRepo {
    private final File file = new File("src/main/resources/data/users.json");
    private final ObjectMapper mapper = new ObjectMapper();
    Map<Long, User> usersMap;


    public Map<Long, User> findAllasMap() throws Exception {
        usersMap = new HashMap<>();
        try {
            mapper.readValue(file, new TypeReference<List<User>>() {
            }).stream().map(m -> usersMap.put(m.getId(), m)).collect(Collectors.toList());

//             mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Users.class));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return usersMap;
    }

    public List<User> findAll() {
        try {
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


}
