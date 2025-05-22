package com.aye.web.controller;

import com.aye.web.dto.ClientServiceIdentificationsResponseDTO;
import com.aye.web.model.ClientInfos;
import com.aye.web.model.ClientServiceIdentifications;
import com.aye.web.service.ClientInfosService;
import com.aye.web.service.ClientServiceIdentificationsService;
import com.aye.web.utill.DtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ClientInfosService clientInfosService;
    private final ClientServiceIdentificationsService csiService;

    public AuthController(ClientInfosService clientInfosService, ClientServiceIdentificationsService csiService) {
        this.clientInfosService = clientInfosService;
        this.csiService = csiService;
    }


    @GetMapping
    public ResponseEntity<ClientServiceIdentificationsResponseDTO> login(Authentication authentication) throws Exception {

        String userName = authentication.getName();
        ClientInfos clientInfosByUserName = clientInfosService.getClientInfoByUserName(userName);
        ClientServiceIdentifications csiInfo = csiService.getByClientInfos(clientInfosByUserName.getId());

        return ResponseEntity.ok(DtoConverter.convertToDTO(csiInfo));

    }
}
