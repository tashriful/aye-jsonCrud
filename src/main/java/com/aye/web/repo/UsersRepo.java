package com.aye.web.repo;

import com.aye.web.model.User;
import com.aye.web.utill.DataLoader;
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
    private final ObjectMapper mapper = new ObjectMapper();
    Map<Long, User> usersMap;


    private final DataLoader dataLoader;

    public UsersRepo(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }


    public Map<Long, User> findAllasMap(String file) throws Exception {
        usersMap = new HashMap<>();
        try {
            dataLoader.loadData(file, User.class).stream().map(m -> usersMap.put(m.getId(), m)).collect(Collectors.toList());

//             mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Users.class));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


        return usersMap;
    }

    public List<Map<String, Map<String, User>>> findAllPrev(String file) {
        try {
            return mapper.readValue(file, new TypeReference<List<Map<String, Map<String, User>>>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Map<String, Map<String, Object>> findAll(String file) {
        try {
            return dataLoader.loadAllFromFile(file);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }


}
