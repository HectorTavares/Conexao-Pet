package com.br.api.domain.request;

import com.br.api.domain.enumaration.Especie;
import com.br.api.domain.enumaration.Genero;
import com.br.api.domain.enumaration.Porte;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CadastrarAnimalDataRequest {

    private String nome;

    private String raca;

    private String situacaoVacinal;

    private String caracteristicas;

    private LocalDate dataDeNascimento;

    private Especie especie;

    private Genero genero;

    private Porte porte;

}
