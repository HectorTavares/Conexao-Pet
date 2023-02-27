package com.br.api.service.voluntario;

import com.br.api.domain.model.Interesse;
import com.br.api.domain.model.Voluntario;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.service.interesse.ValidarInteressesService;
import com.br.api.service.security.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdicionarInteresseService {

    private final VoluntarioRepository voluntarioRepository;

    private final ValidarInteressesService validarInteressesService;

    private final AuthenticatedUserService authenticatedUserService;

    private final BuscarVoluntarioService buscarVoluntarioService;

    public void adicionar(List<String> interessesRequest) {
        Voluntario voluntarioAutenticado = buscarVoluntarioService.porIdUsuario(authenticatedUserService.getId());

        List<Interesse> interessesMapeados = validarInteressesService.getInteresses(interessesRequest);

        voluntarioAutenticado.setInteresses(interessesMapeados);

        voluntarioRepository.save(voluntarioAutenticado);
    }

}
