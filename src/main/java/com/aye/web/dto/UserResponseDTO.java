package com.aye.web.dto;

import java.util.Date;
import java.util.Set;

public class UserResponseDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Set<RoleDTO> roles; // Only role names, not full Role objects
    private Date startDates;
    private Date endDates;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String userName, String firstName, String lastName, Boolean active, Set<RoleDTO> roles, Date startDates, Date endDates) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
        this.startDates = startDates;
        this.endDates = endDates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Date getStartDates() {
        return startDates;
    }

    public void setStartDates(Date startDates) {
        this.startDates = startDates;
    }

    public Date getEndDates() {
        return endDates;
    }

    public void setEndDates(Date endDates) {
        this.endDates = endDates;
    }
}
