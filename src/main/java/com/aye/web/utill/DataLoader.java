package com.aye.web.utill;

import com.aye.web.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader {

    private final ObjectMapper objectMapper;

    /**
     * Generic method to load data from a JSON file.
     * @param filePath path to the JSON file
     * @param className    class of the entity
     * @return List of T
     */


    public DataLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> loadData(String filePath, Class<T> className) throws Exception {
        try{
            File file = new File(filePath);
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, className));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Map<String, Map<String, Object>> loadAllFromFile(String filePath) {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        try {
            if (!file.exists()){
                file.getParentFile().mkdirs();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, new HashMap<String, Map<String, Object>>());

                mapper.writerWithDefaultPrettyPrinter().writeValue(file, new HashMap<String, Map<String, Object>>());
            }

            // Read the full JSON as a Map<String, Map<String, Object>>
            return mapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    public void writeToFile(String file, Map<String, Map<String, Object>> dataList) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(file), dataList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
