package com.aye.web.dto;



import com.aye.web.model.Role;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserRequestDTO {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Long roleId; // just the ID
    private Set<Role> roles= new LinkedHashSet<>();
    private Date startDates;
    private Date endDates;


    public UserRequestDTO() {
    }

    public UserRequestDTO(Long id, String userName, String password, String firstName, String lastName, Boolean active, Long roleId, Set<Role> roles, Date startDates, Date endDates) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
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
