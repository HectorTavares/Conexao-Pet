package com.br.api.service.interesse;

import com.br.api.domain.model.Interesse;
import com.br.api.mapper.EntityInteresseMapper;
import com.br.api.util.CheckIsNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidarInteressesService {

    private final BuscarInteresseService buscarInteresseService;

    private final CheckIsNumber checkIsNumber;

    private final EntityInteresseMapper entityInteresseMapper = new EntityInteresseMapper();

    public List<Interesse> getInteresses(List<String> interesses) {
        return interesses.stream().map(i -> {
            if (checkIsNumber.check(i)) {
                return buscarInteresseService.porId(Long.parseLong(i));
            }

            String interesseLowerCase = i.toLowerCase();

            return buscarInteresseService.porNome(interesseLowerCase).orElseGet(() -> entityInteresseMapper.apply(interesseLowerCase));
        }).collect(Collectors.toList());
    }
}
