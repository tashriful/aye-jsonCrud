package com.aye.web.model;


public class ClientInfos {

    private Long id;
    private String name;
    private String address;
    private String phNumber;
    private Boolean status ;
    private ClientDetails.ClientType type;
    private User user;

    public ClientInfos() {
    }

    public ClientInfos(Long id, String name, String address, String phNumber, Boolean status, ClientDetails.ClientType type, User user) {
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

    public ClientDetails.ClientType getType() {
        return type;
    }

    public void setType(ClientDetails.ClientType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
