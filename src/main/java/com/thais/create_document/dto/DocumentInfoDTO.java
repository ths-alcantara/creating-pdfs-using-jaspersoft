package com.thais.create_document.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocumentInfoDTO {
    private String name;
    private Date dataFinalizacao;
    private String cpf;
    private String course;
    private String professor;
}
