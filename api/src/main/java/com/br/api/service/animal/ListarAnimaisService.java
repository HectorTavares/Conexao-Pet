package com.br.api.service.animal;

import com.br.api.domain.model.Ong;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.mapper.ResponseAnimalMapper;
import com.br.api.service.ong.BuscarOngService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarAnimaisService {

    private final BuscarOngService buscarOngService;

    private final ResponseAnimalMapper mapper = new ResponseAnimalMapper();

    public List<AnimalModelResponse> listar(Long idOng) {
        Ong ong = buscarOngService.porIdOng(idOng);

        return ong.getAnimais().stream().map(mapper::apply).collect(Collectors.toList());
    }


}
