package com.br.api.controller;

import com.br.api.domain.response.InteresseModelResponse;
import com.br.api.service.interesse.ListarInteressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interesse")
public class InteresseController {

    @Autowired
    private ListarInteressesService listarInteressesService;


    @GetMapping("/listar")
    private List<InteresseModelResponse> listaInteresses() {
        return listarInteressesService.listar();
    }
}
