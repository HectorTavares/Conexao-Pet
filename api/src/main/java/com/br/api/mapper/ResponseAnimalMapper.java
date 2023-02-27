package com.br.api.mapper;

import com.br.api.domain.model.Animal;
import com.br.api.domain.response.AnimalModelResponse;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class ResponseAnimalMapper implements Function<Animal, AnimalModelResponse> {

    private final ResponseFotoMapper fotoMapper = new ResponseFotoMapper();

    @Override
    public AnimalModelResponse apply(Animal animal) {
        return AnimalModelResponse.builder()
                .caracteristicas(animal.getCaracteristicas())
                .id(animal.getId())
                .porte(animal.getPorte().getPorte())
                .especie(animal.getEspecie().getEspecie())
                .dataDeNascimento(animal.getDataDeNascimento())
                .nome(animal.getNome())
                .raca(animal.getRaca())
                .telefoneDono(animal.getDono().getUsuario().getTelefone())
                .situacaoVacinal(animal.getSituacaoVacinal())
                .fotos(fotoMapper.apply(animal.getFotos()))
                .genero(animal.getGenero().getGenero())
                .build();
    }
}
