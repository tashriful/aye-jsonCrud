package com.aye.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ClientServiceIdentifications implements Serializable {


    private Long id;
    private ClientInfos clientInfos;
    private String clientName;
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
    private ClientDetails.CsiType csiType;
    private String colorSchema;
    private String colorSchemaApp;
    private String colorSchemaButton;
    private String iconUrl;

    private String appVer;

    public ClientServiceIdentifications() {
    }

    public ClientServiceIdentifications(Long id, ClientInfos clientInfos, String clientName, byte[] imgFile, Date startDates, Date endDates, String csiNumber, String urlPath, ClientDetails.CsiType csiType, String colorSchema, String colorSchemaApp, String colorSchemaButton, String iconUrl, String appVer) {
        this.id = id;
        this.clientInfos = clientInfos;
        this.clientName = clientName;
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

    public ClientInfos getClientInfos() {
        return clientInfos;
    }

    public void setClientInfos(ClientInfos clientInfos) {
        this.clientInfos = clientInfos;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public ClientDetails.CsiType getCsiType() {
        return csiType;
    }

    public void setCsiType(ClientDetails.CsiType csiType) {
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
