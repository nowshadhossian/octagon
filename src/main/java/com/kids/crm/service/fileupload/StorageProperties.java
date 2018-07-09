package com.kids.crm.service.fileupload;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "C:/workspace/upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
