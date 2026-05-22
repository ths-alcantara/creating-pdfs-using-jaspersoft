package com.thais.create_document.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.thais.create_document.dto.DocumentInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class ProcessCSV {

    public List<DocumentInfoDTO> getCsvInfo(MultipartFile csvFile){
       try {
           Reader reader = new BufferedReader(
                   new InputStreamReader(csvFile.getInputStream()));

           CsvToBean<DocumentInfoDTO> csvToBean = new CsvToBeanBuilder<DocumentInfoDTO>(reader)
                   .withType(DocumentInfoDTO.class)
                   .withIgnoreLeadingWhiteSpace(true)
                   .build();

           List<DocumentInfoDTO> infoList = csvToBean.parse();

           return  infoList;

       } catch (IOException e) {
           throw new RuntimeException("Não foi possível ler o arquivo csv.");
       }
    }


}
