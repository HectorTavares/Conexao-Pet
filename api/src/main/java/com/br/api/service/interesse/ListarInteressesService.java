package com.br.api.service.interesse;

import com.br.api.domain.response.InteresseModelResponse;
import com.br.api.mapper.ResponseModelInteresseMapper;
import com.br.api.repository.InteresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarInteressesService {

    private final ResponseModelInteresseMapper mapper = new ResponseModelInteresseMapper();

    private final InteresseRepository repository;

    public List<InteresseModelResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::apply).collect(Collectors.toList());
    }

}
