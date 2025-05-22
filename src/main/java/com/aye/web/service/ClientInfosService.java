package com.aye.web.service;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.exception.CustomExceptions;
import com.aye.web.model.ClientInfos;
import com.aye.web.model.User;
import com.aye.web.repo.ClientInfosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientInfosService {

    private final ObjectMapper objectMapper;
    private final ClientInfosRepository clientInfosRepository;
    private final FileStorageProperties fileStorageProperties;

    public ClientInfosService(ObjectMapper objectMapper, ClientInfosRepository clientInfosRepository, FileStorageProperties fileStorageProperties) {
        this.objectMapper = objectMapper;
        this.clientInfosRepository = clientInfosRepository;
        this.fileStorageProperties = fileStorageProperties;
    }


    public List<ClientInfos> getAllClientInfos() {
        try {
            return clientInfosRepository.findAll(fileStorageProperties.getFilePath()).get("clientInfos").
                    values().
                    stream().
                    map(clientInfoObj -> objectMapper.convertValue(clientInfoObj, ClientInfos.class)).
                    collect(Collectors.toList());
        }
        catch (Exception e){
            throw new CustomExceptions.InternalServerErrorException("Failed to read Clients from file - "+e);
        }
    }

    // Save client info to the JSON file
    public ClientInfos saveClientInfo(ClientInfos clientInfo){

        try {

            Long userId = clientInfo.getUser().getId();

            if (userId == null){
                throw new CustomExceptions.ResourceNotFoundException("user not present in request");
            }

            Map<String, Map<String, Object>> fullData = clientInfosRepository.findAll(fileStorageProperties.getFilePath());

            Map<String, Object> clientInfosMap = fullData.getOrDefault("clientInfos", new HashMap<>());
            Map<String, Object> userMap = fullData.getOrDefault("user", new HashMap<>());

            Object userObject = userMap.get(String.valueOf(userId));

            if (userObject == null) {
                throw new CustomExceptions.ResourceNotFoundException("User ID " + userId + " not found");
            }

            if (clientInfo.getId() == null){
                List<Long> prevIds = clientInfosMap.keySet().stream().map(Long::valueOf).toList();
                Long nextId = prevIds.stream().max(Long::compareTo).orElse(0L)+1;
                clientInfo.setId(nextId);
            }

            User fullUser = objectMapper.convertValue(userObject, User.class);
            clientInfo.setUser(fullUser);

            clientInfosMap.put(clientInfo.getId().toString(), clientInfo);
            fullData.put("clientInfos", clientInfosMap);

            clientInfosRepository.writeToFile(fileStorageProperties.getFilePath(), fullData);

            return objectMapper.convertValue(clientInfosMap.get(clientInfo.getId().toString()), ClientInfos.class);

        }
        catch (CustomExceptions.InternalServerErrorException e) {
            throw new CustomExceptions.InternalServerErrorException(""+e);
        }

    }

    //     Get client info by ID
    public ClientInfos getClientInfoById(Long id){
        Object clientInfos = clientInfosRepository.findAll(fileStorageProperties.getFilePath()).get("clientInfos").get(String.valueOf(id));
        if (clientInfos == null) {
            throw new CustomExceptions.ResourceNotFoundException("Client ->" + id + " not found");
        }
        return objectMapper.convertValue(clientInfos, ClientInfos.class);
    }


    public ClientInfos getClientInfoByUserName(String userName){
        Map<String, Object> clientInfosMap = clientInfosRepository.findAll(fileStorageProperties.getFilePath()).get("clientInfos");

        Optional<ClientInfos> userClientInfos = clientInfosMap.values().stream()
                .map(infoObj -> objectMapper.convertValue(infoObj, ClientInfos.class))
                .filter(info -> info.getUser().getUserName().equals(userName)).findAny();

        if (userClientInfos.isEmpty()){
            throw new CustomExceptions.ResourceNotFoundException("Client Info not found with the username :"+userName);
        }
        return userClientInfos.get();


    }


//
//    // Update client info
//    public void updateClientInfo(Long id, ClientInfos updatedClientInfo) throws IOException {
//        List<ClientInfos> clientInfos = getAllClientInfos();
//        for (int i = 0; i < clientInfos.size(); i++) {
//            if (clientInfos.get(i).getId().equals(id)) {
//                clientInfos.set(i, updatedClientInfo);
//                break;
//            }
//        }
//        objectMapper.writeValue(file, clientInfos);
//    }
//
//    // Delete client info by ID
//    public void deleteClientInfo(Long id) throws IOException {
//        List<ClientInfos> clientInfos = getAllClientInfos();
//        clientInfos.removeIf(clientInfo -> clientInfo.getId().equals(id));
//        objectMapper.writeValue(file, clientInfos);
//    }
}
