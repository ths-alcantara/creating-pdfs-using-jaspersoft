package com.thais.create_document.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.thais.create_document.dto.DocumentInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class ProcessCSV {

    public List<DocumentInfoDTO> getCsvInfo(MultipartFile csvFile) {

        try (
                Reader reader = new BufferedReader(
                        new InputStreamReader(csvFile.getInputStream()))
        ) {

            CsvToBean<DocumentInfoDTO> csvToBean =
                    new CsvToBeanBuilder<DocumentInfoDTO>(reader)
                            .withType(DocumentInfoDTO.class)
                            .withSeparator(';')
                            .withSkipLines(0)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
