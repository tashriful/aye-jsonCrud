package com.aye.web.service;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.exception.CustomExceptions;
import com.aye.web.model.ClientServiceIdentifications;
import com.aye.web.repo.ClientServiceIdentificationsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceIdentificationsService {

    private final ClientServiceIdentificationsRepo csiRepo;
    private final ObjectMapper objectMapper;
    private final FileStorageProperties fileStorageProperties;

    public ClientServiceIdentificationsService(ClientServiceIdentificationsRepo csiRepo, ObjectMapper objectMapper, FileStorageProperties fileStorageProperties) {
        this.csiRepo = csiRepo;
        this.objectMapper = objectMapper;
        this.fileStorageProperties = fileStorageProperties;
    }


    public List<ClientServiceIdentifications> getAll() {

        try {
            return csiRepo.findAll(fileStorageProperties.getFilePath())
                    .getOrDefault("clientServiceIdentifications", new HashMap<>())
                    .values()
                    .stream()
                    .map(obj -> objectMapper.convertValue(obj, ClientServiceIdentifications.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomExceptions.InternalServerErrorException("Failed to read CSI info from file - " + e);
        }
    }

    public ClientServiceIdentifications getById(Long id) {
        try {
            Object obj = csiRepo.findAll(fileStorageProperties.getFilePath())
                    .getOrDefault("clientServiceIdentifications", new HashMap<>())
                    .get(String.valueOf(id));
            if (obj == null) {
                throw new CustomExceptions.ResourceNotFoundException("Client Service Identification ->" + id + " not found");
            }

            return objectMapper.convertValue(obj, ClientServiceIdentifications.class);
        } catch (CustomExceptions.InternalServerErrorException e) {
            throw new CustomExceptions.InternalServerErrorException("Failed to read CSI info from file - " + e);
        }
    }

    public ClientServiceIdentifications getByClientInfos(Long clientInfosId) {
        Map<String, Map<String, Object>> fullData = csiRepo.findAll(fileStorageProperties.getFilePath());
        if (fullData == null) {
            throw new CustomExceptions.ResourceNotFoundException("No Data found in file");
        }

        Map<String, Object> csiMap = fullData.get("clientServiceIdentifications");

        if (csiMap == null || csiMap.isEmpty()) {
            throw new CustomExceptions.ResourceNotFoundException("No client service identifications Data found");
        }

        return csiMap.values().stream().map(obj -> objectMapper.convertValue(obj, ClientServiceIdentifications.class))
                .filter(csi -> csi.getClientInfos() != null && clientInfosId.equals(csi.getClientInfos().getId()))
                .findAny().orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("No client service identification found for clientInfosId: " + clientInfosId));
    }


    public ClientServiceIdentifications save(ClientServiceIdentifications csi) {

        Map<String, Map<String, Object>> fullData = csiRepo.findAll(fileStorageProperties.getFilePath());
        if (fullData == null) {
            throw new CustomExceptions.ResourceNotFoundException("No Data found in file");
        }

        Map<String, Object> csiMap = fullData.getOrDefault("clientServiceIdentifications", new HashMap<>());

        if (csi.getId() == null) {
            List<Long> prevIds = csiMap.keySet().stream().map(Long::valueOf).toList();
            Long nextId = prevIds.stream().max(Long::compareTo).orElse(0L) + 1;
            csi.setId(nextId);
        }

        csiMap.put(csi.getId().toString(), csi);
        fullData.put("clientServiceIdentifications", csiMap);
        writeToFile(fullData);
        return csi;

    }

    public void delete(Long id) throws Exception {
        Map<String, Map<String, Object>> fullData = csiRepo.findAll(fileStorageProperties.getFilePath());
        Map<String, Object> csiMap = fullData.getOrDefault("clientServiceIdentifications", new HashMap<>());

        csiMap.remove(String.valueOf(id));
        fullData.put("clientServiceIdentifications", csiMap);
        writeToFile(fullData);
    }

    private void writeToFile(Map<String, Map<String, Object>> data) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileStorageProperties.getFilePath()), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
