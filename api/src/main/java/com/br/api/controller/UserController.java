package com.br.api.controller;

import com.br.api.domain.request.AtualizarUsuarioRequest;
import com.br.api.domain.request.BuscarUsuariosEAnimaisResponse;
import com.br.api.domain.response.OngModelResponse;
import com.br.api.domain.response.OngVoluntarioModelResponse;
import com.br.api.domain.response.VoluntarioModelReponse;
import com.br.api.service.security.BuscarUsuarioAutenticadoStrategy;
import com.br.api.service.usuario.AtualizarUsuarioService;
import com.br.api.service.usuario.BuscarOngVoluntarioService;
import com.br.api.service.usuario.BuscarUsuarioService;
import com.br.api.util.DecodeISO_8859_1Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BuscarUsuarioAutenticadoStrategy service;

    @Autowired
    private BuscarOngVoluntarioService buscarOngVoluntarioService;

    @Autowired
    private AtualizarUsuarioService atualizarUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private DecodeISO_8859_1Util decoder;

    @GetMapping("/buscar/{id}")
    private OngVoluntarioModelResponse buscarOngVoluntario(@PathVariable Long id) {
        return buscarOngVoluntarioService.buscar(id);
    }

    @GetMapping("/buscar")
    private BuscarUsuariosEAnimaisResponse buscarAnimaisEUsuarios(@RequestParam String nome) {
        return buscarUsuarioService.buscar(nome);
    }

    @GetMapping("/ong")
    public OngModelResponse buscarOngAutenticada() {
        return (OngModelResponse) service.run();
    }

    @GetMapping("/voluntario")
    public VoluntarioModelReponse buscarVoluntarioAutenticado() {
        return (VoluntarioModelReponse) service.run();
    }

    @PutMapping("/atualizar")
    public void atualizarUsuario(@RequestPart String data, @RequestPart @Nullable MultipartFile foto) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String decodedData = decoder.decode(data);

        AtualizarUsuarioRequest request = objectMapper.readValue(decodedData, AtualizarUsuarioRequest.class);

        atualizarUsuarioService.atualizar(request, foto);
    }

}
