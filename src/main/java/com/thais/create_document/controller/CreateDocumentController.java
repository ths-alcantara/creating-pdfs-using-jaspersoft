package com.thais.create_document.controller;

import com.thais.create_document.service.ProcessDocuments;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;

@RestController
public class CreateDocumentController {

    private final ProcessDocuments createDocument;

    public CreateDocumentController(ProcessDocuments createDocument) {
        this.createDocument = createDocument;
    }

    @PostMapping
    public HttpResponse<Object> createDoc(MultipartFile csvFile){
        Object documents = createDocument.createDoc(csvFile);
        return (HttpResponse<Object>) documents;
    }
}
