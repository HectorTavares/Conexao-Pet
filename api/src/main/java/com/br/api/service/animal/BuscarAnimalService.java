package com.br.api.service.animal;

import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.mapper.ResponseAnimalMapper;
import com.br.api.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarAnimalService {

    private final AnimalRepository animalRepository;

    private final ResponseAnimalMapper mapper = new ResponseAnimalMapper();

    public List<AnimalModelResponse> buscar(String busca) {
        return animalRepository.buscarAnimaisPorNomeRaca(busca).stream().map(mapper::apply).collect(Collectors.toList());
    }


}
