package com.aye.web.model;

import java.io.Serializable;
import java.util.Date;

public class ClientServiceIdentifications implements Serializable {


    private Long id;
    private ClientInfos clientInfos;
    private String clientName;
    private byte[] imgFile;
    private Date startDates;
    private Date endDates;
    private String csiNumber;
    private String urlPath;
    private String csiType;
    private String colorSchema;
    private String colorSchemaApp;
    private String colorSchemaButton;
    private String iconUrl;

    private String appVer;

}
