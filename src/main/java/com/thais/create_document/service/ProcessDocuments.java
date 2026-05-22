package com.thais.create_document.service;

import com.thais.create_document.dto.DocumentInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcessDocuments {

    private final ProcessCSV processCSV;

    public ProcessDocuments(ProcessCSV processCSV) {
        this.processCSV = processCSV;
    }

    public Object createDoc(MultipartFile csvFile){
        DocumentInfoDTO documentInfo = processCSV.getCsvInfo(csvFile);
        // passar os dados documentInfo para os atributos (for)
        // salvar em pdfs
        //zip
        return //arquivos compactados
    }
}
