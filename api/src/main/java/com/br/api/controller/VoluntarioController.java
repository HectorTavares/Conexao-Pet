package com.br.api.controller;

import com.br.api.domain.request.AdicionarInteressesRequest;
import com.br.api.domain.request.CadastrarVoluntarioRequest;
import com.br.api.domain.response.CampanhaModelResponse;
import com.br.api.service.voluntario.AdicionarInteresseService;
import com.br.api.service.voluntario.BuscarFeedVoluntarioService;
import com.br.api.service.voluntario.CadastrarVoluntarioService;
import com.br.api.service.voluntario.SeguirOngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

    @Autowired
    private CadastrarVoluntarioService cadastrarVoluntarioService;

    @Autowired
    private SeguirOngService seguirOngService;

    @Autowired
    private AdicionarInteresseService adicionarInteresseService;

    @Autowired
    private BuscarFeedVoluntarioService buscarFeedVoluntarioService;

    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    private void cadastrarVoluntario(@RequestBody CadastrarVoluntarioRequest request) {
        cadastrarVoluntarioService.cadastrar(request);
    }

    @PostMapping("/seguir/{id}")
    @ResponseStatus(OK)
    private void seguirOng(@PathVariable Long id) {
        seguirOngService.seguir(id);
    }

    @PostMapping("/adicionar/interesses")
    @ResponseStatus(OK)
    private void adicionarInteresses(@RequestBody AdicionarInteressesRequest request) {
        adicionarInteresseService.adicionar(request.getInteresses());
    }

    @GetMapping("/feed")
    private List<CampanhaModelResponse> buscarFeedUsuario() {
        return buscarFeedVoluntarioService.buscar();
    }

}
