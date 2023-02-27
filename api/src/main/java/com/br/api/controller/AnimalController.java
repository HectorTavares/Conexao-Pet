package com.br.api.controller;

import com.br.api.domain.request.CadastrarAnimalDataRequest;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.service.animal.BuscarAnimalService;
import com.br.api.service.animal.ListarAnimaisService;
import com.br.api.service.ong.CadastrarAnimalService;
import com.br.api.util.DecodeISO_8859_1Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private CadastrarAnimalService cadastrarAnimalService;

    @Autowired
    private ListarAnimaisService listarAnimaisService;

    @Autowired
    private BuscarAnimalService buscarAnimalService;

    @Autowired
    private DecodeISO_8859_1Util decoder;

    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    private void cadastrarAnimal(@RequestPart String data, @RequestPart List<MultipartFile> fotos) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String decodedData = decoder.decode(data);

        CadastrarAnimalDataRequest request = objectMapper.readValue(decodedData, CadastrarAnimalDataRequest.class);

        cadastrarAnimalService.cadastrar(request, fotos);
    }

    @GetMapping("/listar/{id}")
    private List<AnimalModelResponse> listarAnimais(@PathVariable Long id) {
        return listarAnimaisService.listar(id);
    }
}
