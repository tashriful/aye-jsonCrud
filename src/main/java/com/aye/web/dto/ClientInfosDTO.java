package com.aye.web.dto;

public class ClientInfosDTO {

    private Long id;
    private String name;
    private String address;
    private String phNumber;
    private Boolean status;
    private String type;
    private UserResponseDTO user;

    public ClientInfosDTO() {
    }

    public ClientInfosDTO(Long id, String name, String address, String phNumber, Boolean status, String type, UserResponseDTO user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phNumber = phNumber;
        this.status = status;
        this.type = type;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
