package com.br.api.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AnimalModelResponse {

    private Long id;

    private String especie;

    private String genero;

    private String porte;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDeNascimento;

    private String nome;

    private String telefoneDono;

    private String raca;

    private String situacaoVacinal;

    private String caracteristicas;

    private List<String> fotos;

}
