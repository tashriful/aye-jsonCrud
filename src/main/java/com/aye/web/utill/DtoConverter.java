package com.aye.web.utill;

import com.aye.web.dto.ClientInfosDTO;
import com.aye.web.dto.ClientServiceIdentificationsResponseDTO;
import com.aye.web.dto.RoleDTO;
import com.aye.web.dto.UserResponseDTO;
import com.aye.web.model.ClientInfos;
import com.aye.web.model.ClientServiceIdentifications;
import com.aye.web.model.Role;
import com.aye.web.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

public class DtoConverter {

    public static UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getActive(),
                user.getRoles().stream().map(DtoConverter::convertToDTO).collect(Collectors.toSet()),
                user.getStartDates(),
                user.getEndDates());
    }

    public static RoleDTO convertToDTO(Role role){
        return new RoleDTO(role.getId() , role.getRole());
    }

    public static ClientInfosDTO convertToDTO(ClientInfos entity) {
        if (entity == null) return null;

        ClientInfosDTO dto = new ClientInfosDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhNumber(entity.getPhNumber());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType() != null ? entity.getType().name() : null);
        dto.setUser(DtoConverter.convertToDTO(entity.getUser()));

        return dto;
    }

    public static ClientServiceIdentificationsResponseDTO convertToDTO(ClientServiceIdentifications model) {
        if (model == null) {
            return null;
        }

        ClientServiceIdentificationsResponseDTO dto = new ClientServiceIdentificationsResponseDTO();

        ClientInfos clientInfos = model.getClientInfos();

        dto.setId(model.getId());
        dto.setClientId(clientInfos != null ? clientInfos.getId() : null);
        dto.setClientName(clientInfos.getName());
        dto.setClientType(clientInfos != null ? clientInfos.getType().name() : null);
        dto.setClientStatus(clientInfos != null ? clientInfos.getStatus() : null);
        dto.setAddress(clientInfos != null ? clientInfos.getAddress() : null);
        dto.setPhoneNumber(clientInfos != null ? clientInfos.getPhNumber() : null);
        dto.setImgFile(model.getImgFile());
        dto.setStartDates(model.getStartDates());
        dto.setEndDates(model.getEndDates());
        dto.setCsiNumber(model.getCsiNumber());
        dto.setUrlPath(model.getUrlPath());
        dto.setCsiType(model.getCsiType() != null ? model.getCsiType().name() : null);
        dto.setColorSchema(model.getColorSchema());
        dto.setColorSchemaApp(model.getColorSchemaApp());
        dto.setColorSchemaButton(model.getColorSchemaButton());
        dto.setIconUrl(model.getIconUrl());
        dto.setAppVer(model.getAppVer());

        return dto;
    }

//    public static ClientServiceIdentificationsResponseDTO convertToDTO(ClientServiceIdentifications entity) {
//        ClientServiceIdentificationsResponseDTO dto = new ClientServiceIdentificationsResponseDTO();
//        dto.setId(entity.getId());
//        dto.setClientId(entity.getClientInfos() != null ? entity.getClientInfos().getId() : null);
//        dto.setClientName(entity.getClientName());
//        dto.setClientType(entity.getClientInfos() != null && entity.getClientInfos().getType() != null
//                ? entity.getClientInfos().getType().name() : null);
//        dto.setClientStatus(entity.getClientInfos() != null ? entity.getClientInfos().getStatus() : null);
//        dto.setAddress(entity.getClientInfos() != null ? entity.getClientInfos().getAddress() : null);
//        dto.setPhoneNumber(entity.getClientInfos() != null ? entity.getClientInfos().getPhNumber() : null);
//        dto.setCsiNumber(entity.getCsiNumber());
//        dto.setUrlPath(entity.getUrlPath());
//        dto.setCsiType(entity.getCsiType() != null ? entity.getCsiType().name() : null);
//        dto.setStartDates(entity.getStartDates());
//        dto.setEndDates(entity.getEndDates());
//        dto.setColorSchema(entity.getColorSchema());
//        dto.setColorSchemaApp(entity.getColorSchemaApp());
//        dto.setColorSchemaButton(entity.getColorSchemaButton());
//        dto.setIconUrl(entity.getIconUrl());
//        dto.setAppVer(entity.getAppVer());
//        return dto;
//    }


}
