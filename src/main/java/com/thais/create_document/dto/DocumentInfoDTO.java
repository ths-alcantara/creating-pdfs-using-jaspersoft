package com.thais.create_document.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DocumentInfoDTO {

    @CsvBindByName(column = "\uFEFFname")
    private String name;

    @CsvBindByName(column = "date")
    private String date;

    @CsvBindByName(column = "cpf")
    private String cpf;

    @CsvBindByName(column = "course")
    private String course;

    @CsvBindByName(column = "professor")
    private String professor;

    @CsvBindByName(column = "school")
    private String school;

    private LocalDateTime certificateDate;
}