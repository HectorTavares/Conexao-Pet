package com.br.api.mapper;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Foto;
import com.br.api.domain.model.Interesse;
import com.br.api.domain.request.CriarCampanhaDataRequest;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;
import java.util.stream.Collectors;

public class EntityCampanhaMapper implements TriFunction<CriarCampanhaDataRequest, List<Interesse>, List<String>, Campanha> {

    private final EntityFotoMapper entityFotoMapper = new EntityFotoMapper();

    @Override
    public Campanha apply(CriarCampanhaDataRequest request, List<Interesse> interesses, List<String> urls) {
        return Campanha.builder()
                .titulo(request.getTitulo())
                .dataEncerramento(request.getDataEncerramento())
                .descricao(request.getDescricao())
                .interesses(interesses)
                .fotos(fotosToEntity(urls))
                .build();
    }

    private List<Foto> fotosToEntity(List<String> urls) {
        return urls.stream().map(entityFotoMapper::apply).collect(Collectors.toList());
    }
}
