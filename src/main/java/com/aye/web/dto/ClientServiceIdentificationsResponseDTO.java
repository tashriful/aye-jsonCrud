package com.aye.web.dto;

import com.aye.web.model.ClientDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ClientServiceIdentificationsResponseDTO {

    private Long id;
    private Long clientId;
    private String clientName;
    private String clientType;
    private Boolean clientStatus;
    private String address;
    private String phoneNumber;

    private byte[] imgFile;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date startDates;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date endDates;
    private String csiNumber;
    private String urlPath;
    private String csiType;
    private String colorSchema;
    private String colorSchemaApp;
    private String colorSchemaButton;
    private String iconUrl;

    private String appVer;

    // Getters and Setters


    public ClientServiceIdentificationsResponseDTO() {
    }

    public ClientServiceIdentificationsResponseDTO(Long id, Long clientId, String clientName, String clientType, Boolean clientStatus, String address, String phoneNumber, byte[] imgFile, Date startDates, Date endDates, String csiNumber, String urlPath, String csiType, String colorSchema, String colorSchemaApp, String colorSchemaButton, String iconUrl, String appVer) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientType = clientType;
        this.clientStatus = clientStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imgFile = imgFile;
        this.startDates = startDates;
        this.endDates = endDates;
        this.csiNumber = csiNumber;
        this.urlPath = urlPath;
        this.csiType = csiType;
        this.colorSchema = colorSchema;
        this.colorSchemaApp = colorSchemaApp;
        this.colorSchemaButton = colorSchemaButton;
        this.iconUrl = iconUrl;
        this.appVer = appVer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Boolean getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(Boolean clientStatus) {
        this.clientStatus = clientStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImgFile() {
        return imgFile;
    }

    public void setImgFile(byte[] imgFile) {
        this.imgFile = imgFile;
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

    public String getCsiNumber() {
        return csiNumber;
    }

    public void setCsiNumber(String csiNumber) {
        this.csiNumber = csiNumber;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getCsiType() {
        return csiType;
    }

    public void setCsiType(String csiType) {
        this.csiType = csiType;
    }

    public String getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(String colorSchema) {
        this.colorSchema = colorSchema;
    }

    public String getColorSchemaApp() {
        return colorSchemaApp;
    }

    public void setColorSchemaApp(String colorSchemaApp) {
        this.colorSchemaApp = colorSchemaApp;
    }

    public String getColorSchemaButton() {
        return colorSchemaButton;
    }

    public void setColorSchemaButton(String colorSchemaButton) {
        this.colorSchemaButton = colorSchemaButton;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }
}
