package com.kids.crm.controller;

import com.kids.crm.service.fileupload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/files")
public class FileController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/load-image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadImage(@PathVariable("filename") String fileName){
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
