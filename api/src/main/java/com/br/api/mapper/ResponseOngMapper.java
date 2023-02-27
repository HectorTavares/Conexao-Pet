package com.br.api.mapper;

import com.br.api.domain.model.Animal;
import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Ong;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.domain.response.CampanhaModelResponse;
import com.br.api.domain.response.OngModelResponse;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseOngMapper implements Function<Ong, OngModelResponse> {

    private final ResponseAnimalMapper animalMapper = new ResponseAnimalMapper();

    private final ResponseCampanhaMapper campanhaMapper = new ResponseCampanhaMapper();

    private final ResponseFotoMapper fotoMapper = new ResponseFotoMapper();

    private final PermissionMapper permissionMapper = new PermissionMapper();

    @Override
    public OngModelResponse apply(Ong ong) {
        return OngModelResponse.builder()
                .id(ong.getId())
                .idUsuario(ong.getUsuario().getId())
                .email(ong.getUsuario().getEmail())
                .nome(ong.getUsuario().getNome())
                .foto(ong.getUsuario().getFoto())
                .imagens(fotoMapper.apply(ong.getImagens()))
                .endereco(ong.getUsuario().getEndereco())
                .permissions(permissionMapper.listPermissionsResponse(ong.getUsuario().getPermissions()))
                .telefone(ong.getUsuario().getTelefone())
                .animais(animalsToResponse(ong.getAnimais()))
                .campanhas(campanhasToResponse(ong.getCampanhas()))
                .build();
    }


    private List<AnimalModelResponse> animalsToResponse(List<Animal> animals) {
        return animals.stream().map(animalMapper::apply).collect(Collectors.toList());
    }

    private List<CampanhaModelResponse> campanhasToResponse(List<Campanha> campanhas) {
        return campanhas.stream().map(campanhaMapper::apply).sorted(Comparator.comparing(CampanhaModelResponse::getDataCriacao).reversed()).collect(Collectors.toList());
    }

}
