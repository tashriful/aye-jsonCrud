package com.aye.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageProperties {

    @Value("${user.data.file-path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}
