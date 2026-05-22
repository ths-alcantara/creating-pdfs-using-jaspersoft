package com.thais.create_document.controller;

import com.thais.create_document.service.ProcessDocuments;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;

@RestController
public class CreateDocumentController {

    private final ProcessDocuments createDocument;

    public CreateDocumentController(ProcessDocuments createDocument) {
        this.createDocument = createDocument;
    }

    @PostMapping("/documents")
    public ResponseEntity<byte[]> generateDocuments(
            @RequestParam("file") MultipartFile file) throws Exception {

        byte[] zip = createDocument.createDoc(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=documentos.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zip);
    }
}
