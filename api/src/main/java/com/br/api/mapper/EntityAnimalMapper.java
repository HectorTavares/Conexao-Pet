package com.br.api.mapper;

import com.br.api.domain.model.Animal;
import com.br.api.domain.request.CadastrarAnimalDataRequest;

import java.util.List;
import java.util.function.BiFunction;

public class EntityAnimalMapper implements BiFunction<CadastrarAnimalDataRequest, List<String>, Animal> {

    private final EntityFotoMapper entityFotoMapper = new EntityFotoMapper();

    @Override
    public Animal apply(CadastrarAnimalDataRequest cadastrarAnimalDataRequest, List<String> fotos) {
        return Animal.builder()
                .caracteristicas(cadastrarAnimalDataRequest.getCaracteristicas())
                .dataDeNascimento(cadastrarAnimalDataRequest.getDataDeNascimento())
                .especie(cadastrarAnimalDataRequest.getEspecie())
                .genero(cadastrarAnimalDataRequest.getGenero())
                .nome(cadastrarAnimalDataRequest.getNome())
                .situacaoVacinal(cadastrarAnimalDataRequest.getSituacaoVacinal())
                .raca(cadastrarAnimalDataRequest.getRaca())
                .porte(cadastrarAnimalDataRequest.getPorte())
                .fotos(entityFotoMapper.apply(fotos))
                .isExcluido(false)
                .build();
    }
}
