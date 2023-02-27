package com.br.api.controller;

import com.br.api.domain.request.CadastrarOngRequest;
import com.br.api.domain.request.CriarCampanhaDataRequest;
import com.br.api.service.ong.CadastrarOngService;
import com.br.api.service.ong.CriarCampanhaService;
import com.br.api.util.DecodeISO_8859_1Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private CadastrarOngService cadastrarOngService;

    @Autowired
    private CriarCampanhaService criarCampanhaService;

    @Autowired
    private DecodeISO_8859_1Util decoder;

    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    private void cadastrarOng(@RequestBody CadastrarOngRequest request) {
        cadastrarOngService.cadastrar(request);
    }

    @PostMapping(path = "/campanha/criar")
    @ResponseStatus(CREATED)
    private void criarCampanha(@RequestPart String data, @RequestPart List<MultipartFile> fotos) throws JsonProcessingException, UnsupportedEncodingException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String decodedData = decoder.decode(data);

        CriarCampanhaDataRequest request = objectMapper.readValue(decodedData, CriarCampanhaDataRequest.class);

        criarCampanhaService.criar(request, fotos);
    }
}
