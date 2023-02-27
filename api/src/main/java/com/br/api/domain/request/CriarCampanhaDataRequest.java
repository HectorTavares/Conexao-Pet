package com.br.api.domain.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CriarCampanhaDataRequest {

    private String titulo;

    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEncerramento;

    private List<String> interesses;

    @JsonCreator
    public CriarCampanhaDataRequest(@JsonProperty("titulo") String titulo, @JsonProperty("descricao") String descricao,
                                    @JsonProperty("dataEncerramento") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataEncerramento, @JsonProperty("interesses") List<String> interesses) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEncerramento = dataEncerramento;
        this.interesses = interesses;
    }
}
