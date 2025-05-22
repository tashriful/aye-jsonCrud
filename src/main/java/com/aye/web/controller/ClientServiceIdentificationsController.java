package com.aye.web.controller;

import com.aye.web.dto.ClientServiceIdentificationsResponseDTO;
import com.aye.web.exception.CustomExceptions;
import com.aye.web.model.ClientDetails;
import com.aye.web.model.ClientInfos;
import com.aye.web.model.ClientServiceIdentifications;
import com.aye.web.service.ClientInfosService;
import com.aye.web.service.ClientServiceIdentificationsService;
import com.aye.web.utill.DtoConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/csi")
public class ClientServiceIdentificationsController {
    private final ClientServiceIdentificationsService csiService;
    private final ObjectMapper objectMapper;
    private final ClientInfosService clientInfosService;

    public ClientServiceIdentificationsController(ClientServiceIdentificationsService csiService, ObjectMapper objectMapper, ClientInfosService clientInfosService) {
        this.csiService = csiService;
        this.objectMapper = objectMapper;
        this.clientInfosService = clientInfosService;
    }

    @GetMapping
    public ResponseEntity<List<ClientServiceIdentificationsResponseDTO>> getAll() throws Exception {
        List<ClientServiceIdentificationsResponseDTO> csiList = csiService.getAll().stream().map(DtoConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(csiList);
    }

    @GetMapping("/{id}")
    public ClientServiceIdentificationsResponseDTO getById(@PathVariable Long id) throws Exception {
        ClientServiceIdentifications csi = csiService.getById(id);
        return DtoConverter.convertToDTO(csi);
    }

//    @PostMapping(consumes = "multipart/form-data")
//    public ResponseEntity<ClientServiceIdentifications> save(
//            @RequestPart("data") String data,
//            @RequestPart(value = "imgFile", required = false) MultipartFile imgFile
//    ) throws IOException {
//
//        ClientServiceIdentifications csi = objectMapper.readValue(data, ClientServiceIdentifications.class);
//
//        if (imgFile != null && !imgFile.isEmpty()) {
//            csi.setImgFile(imgFile.getBytes());
//        }
//
//        return ResponseEntity.ok(service.save(csi));
//    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClientServiceIdentificationsResponseDTO> createCSI(
            @RequestParam("csiNumber") String csiNumber,
            @RequestParam("clientInfosId") Long clientInfosId,
            @RequestParam("urlPath") String urlPath,
            @RequestParam("csiType") ClientDetails.CsiType csiType,
            @RequestParam("appVer") String appVer,
            @RequestParam(value = "startDates", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDates,
            @RequestParam(value = "endDates", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDates,
            @RequestPart(value = "imgFile", required = false) MultipartFile imgFile
    ) throws IOException {

        if(clientInfosId == null){
            throw new CustomExceptions.ResourceNotFoundException(" client Infos Id Not found In Request");
        }

        ClientServiceIdentifications csi = new ClientServiceIdentifications();
        csi.setCsiNumber(csiNumber);
        csi.setUrlPath(urlPath);
        csi.setCsiType(csiType);
        csi.setAppVer(appVer);
        csi.setStartDates(startDates);
        csi.setEndDates(endDates);
        ClientInfos clientInfoById = clientInfosService.getClientInfoById(clientInfosId);

        if (clientInfoById == null ){
            throw new CustomExceptions.ResourceNotFoundException(" client Infos  Not found with id - "+clientInfosId);
        }
        csi.setClientInfos(clientInfoById);
        csi.setClientName(clientInfoById.getName());

        if (imgFile != null && !imgFile.isEmpty()) {
            csi.setImgFile(imgFile.getBytes());
        }

        ClientServiceIdentifications saved = csiService.save(csi);
        return ResponseEntity.ok(DtoConverter.convertToDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        csiService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
