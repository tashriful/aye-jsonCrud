package com.aye.web.controller;

import com.aye.web.dto.ClientInfosDTO;
import com.aye.web.model.ClientInfos;
import com.aye.web.service.ClientInfosService;
import com.aye.web.utill.DtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientInfos")
public class ClientInfosController {


    private final ClientInfosService clientInfosService;

    public ClientInfosController(ClientInfosService clientInfosService) {
        this.clientInfosService = clientInfosService;
    }

    @GetMapping
    public ResponseEntity<List<ClientInfosDTO>> getAllClients() {

        List<ClientInfos> clientInfos = clientInfosService.getAllClientInfos();
        List<ClientInfosDTO> clientInfosDTOS = clientInfos.stream().map(DtoConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(clientInfosDTOS);

    }

    // Get client info by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientInfosDTO> getClientById(@PathVariable Long id) {

        ClientInfos clientInfo = clientInfosService.getClientInfoById(id);
        return ResponseEntity.ok(DtoConverter.convertToDTO(clientInfo));

    }

    // Create a new client info
    @PostMapping
    public ResponseEntity<ClientInfosDTO> createClient(@RequestBody ClientInfos clientInfo) {
            ClientInfos clientInfos = clientInfosService.saveClientInfo(clientInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convertToDTO(clientInfo));

    }

//    // Update an existing client info
//    @PutMapping("/{id}")
//    public ResponseEntity<ClientInfos> updateClient(@PathVariable Long id, @RequestBody ClientInfos clientInfo) {
//        try {
//            Optional<ClientInfos> existingClient = clientInfosRepository.getClientInfoById(id);
//            if (existingClient.isPresent()) {
//                clientInfosRepository.updateClientInfo(id, clientInfo);
//                return ResponseEntity.ok(clientInfo);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    // Delete a client info by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
//        try {
//            Optional<ClientInfos> clientInfo = clientInfosRepository.getClientInfoById(id);
//            if (clientInfo.isPresent()) {
//                clientInfosRepository.deleteClientInfo(id);
//                return ResponseEntity.noContent().build();
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
