package com.aye.web.service;

import com.aye.web.config.FileStorageProperties;
import com.aye.web.model.User;
import com.aye.web.utill.CustomUserDetails;
import com.aye.web.utill.DataLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsonUserDetailsService implements UserDetailsService {

    private final DataLoader dataLoader;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileStorageProperties fileStorageProperties;

    public JsonUserDetailsService(DataLoader dataLoader, FileStorageProperties fileStorageProperties) {
        this.dataLoader = dataLoader;
        this.fileStorageProperties = fileStorageProperties;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Map<String, Map<String, Object>> allFromFile = dataLoader.loadAllFromFile(fileStorageProperties.getFilePath());

            Map<String, Object> userMap = allFromFile.getOrDefault("user", new HashMap<>());

            List<User> userList = userMap.values().stream().map(userObj -> objectMapper.convertValue(userObj, User.class)).collect(Collectors.toList());

            return userList.stream()
                    .filter(u -> username.equals(u.getUserName()))
                    .findFirst()
                    .map(CustomUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

//            matchedUser.getRoles().stream().map(m-> new SimpleGrantedAuthority("ROLE_" + m.getRole())).collect(Collectors.toList());
//
//
//            Set<GrantedAuthority> authorities = matchedUser.getRoles().stream()
//                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
//                    .collect(Collectors.toSet());
//
//            return new org.springframework.security.core.userdetails.User(
//                    matchedUser.getUserName(),
//                    matchedUser.getPassword(),
////                    matchedUser.getActive() != null && matchedUser.getActive(), // enabled
//                    true,
//                    true, true, true, // account non-expired, non-locked, credentials non-expired
//                    authorities
//            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to load user from JSON", e);
        }
    }
}
