package com.br.api.domain.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CriarCampanhaRequest {

    private CriarCampanhaDataRequest data;

    private List<MultipartFile> fotos;

}
