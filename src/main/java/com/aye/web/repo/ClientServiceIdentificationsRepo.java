package com.aye.web.repo;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.utill.DataLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class ClientServiceIdentificationsRepo {

    private final ObjectMapper objectMapper;
    private final DataLoader dataLoader;
    private final FileStorageProperties fileStorageProperties;



    public ClientServiceIdentificationsRepo(ObjectMapper objectMapper, DataLoader dataLoader, FileStorageProperties fileStorageProperties) {
        this.objectMapper = objectMapper;
        this.dataLoader = dataLoader;
        this.fileStorageProperties = fileStorageProperties;
    }


    public Map<String, Map<String, Object>> findAll(String filePath) {
        try {
            return dataLoader.loadAllFromFile(filePath);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public void writeToFile(String filePath, Map<String, Map<String, Object>> dataList) {
        dataLoader.writeToFile(filePath , dataList);
    }
}
