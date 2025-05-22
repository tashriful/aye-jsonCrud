package com.aye.web.service;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.dto.RoleDTO;
import com.aye.web.model.Role;
import com.aye.web.repo.RoleRepo;
import com.aye.web.repo.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepo roleRepo;
    private final ObjectMapper objectMapper;
    private final FileStorageProperties fileStorageProperties;

    public RoleService(RoleRepo roleRepo, ObjectMapper objectMapper, FileStorageProperties fileStorageProperties) {
        this.roleRepo = roleRepo;
        this.objectMapper = objectMapper;
        this.fileStorageProperties = fileStorageProperties;
    }

    public List<Role> getAllRoles() {
        try {
            Map<String, Map<String, Object>> fullData = roleRepo.findAll(fileStorageProperties.getFilePath());
            Map<String, Object> roleMap = fullData.getOrDefault("role", new HashMap<>());
            return roleMap.values().stream()
                    .map(obj -> objectMapper.convertValue(obj, Role.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read roles from file", e);
        }
    }

    public Role getRoleById(Long id) {
        try {
            Map<String, Object> roleMap = roleRepo.findAll(fileStorageProperties.getFilePath()).getOrDefault("role", new HashMap<>());
            Object roleObj = roleMap.get(String.valueOf(id));
            if (roleObj == null) return null;
            return objectMapper.convertValue(roleObj, Role.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read role from file", e);
        }
    }

    public Role createRole(Role role) {
        try {
            Map<String, Map<String, Object>> fullData = roleRepo.findAll(fileStorageProperties.getFilePath());
            Map<String, Object> roleMap = fullData.getOrDefault("role", new HashMap<>());

            if (role.getId() == null) {
                List<Long> prevIds = roleMap.keySet().stream().map(Long::valueOf).toList();
                Long nextId = prevIds.stream().max(Long::compareTo).orElse(0L) + 1;
                role.setId(nextId);
            }

            roleMap.put(role.getId().toString(), role);
            fullData.put("role", roleMap);

            writeToFile(fullData);
            return role;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create role", e);
        }
    }

    private void writeToFile(Map<String, Map<String, Object>> fullData) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileStorageProperties.getFilePath()), fullData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write role data to file", e);
        }
    }


}
