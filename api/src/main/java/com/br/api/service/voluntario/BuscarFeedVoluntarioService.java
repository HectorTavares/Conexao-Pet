package com.br.api.service.voluntario;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.response.CampanhaModelResponse;
import com.br.api.mapper.ResponseCampanhaMapper;
import com.br.api.repository.CampanhaRepository;
import com.br.api.service.security.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarFeedVoluntarioService {

    private final ResponseCampanhaMapper responseCampanhaMapper = new ResponseCampanhaMapper();
    private final CampanhaRepository campanhaRepository;

    private final AuthenticatedUserService authenticatedUserService;

    private final BuscarVoluntarioService buscarVoluntarioService;

    public List<CampanhaModelResponse> buscar() {
        Voluntario voluntarioAutenticado = buscarVoluntarioService.porIdUsuario(authenticatedUserService.getId());

        Long idVoluntario = voluntarioAutenticado.getId();

        List<Campanha> campanhasFeedPorInteresses = campanhaRepository.buscarFeedPorInteresses(idVoluntario);

        List<Campanha> campanhaFeedPorOngsSeguidas = campanhaRepository.buscarFeedPorOngsSeguidas(idVoluntario);

        return validarCampanhasFeed(campanhasFeedPorInteresses, campanhaFeedPorOngsSeguidas);
    }

    private List<CampanhaModelResponse> validarCampanhasFeed(List<Campanha> campanhasInteresses, List<Campanha> campanhasOngsSeguidas) {
        List<Campanha> campanhasFiltradas = new ArrayList<>();
        campanhasFiltradas.addAll(campanhasInteresses);
        campanhasFiltradas.addAll(campanhasOngsSeguidas);

        return campanhasFiltradas.stream().distinct().map(responseCampanhaMapper::apply).sorted(Comparator.comparing(CampanhaModelResponse::getDataCriacao).reversed()).collect(Collectors.toList());
    }

}
