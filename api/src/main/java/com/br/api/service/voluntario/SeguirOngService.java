package com.br.api.service.voluntario;

import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Voluntario;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.service.ong.BuscarOngService;
import com.br.api.service.security.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeguirOngService {

    private final VoluntarioRepository voluntarioRepository;

    private final AuthenticatedUserService authenticatedUserService;

    private final BuscarVoluntarioService buscarVoluntarioService;

    private final BuscarOngService buscarOngService;

    private final VerificaVoluntarioSegueOngService verificaVoluntarioSegueOngService;

    private final UnfollowOngService unfollowOngService;

    public void seguir(Long id) {
        Voluntario voluntario = buscarVoluntarioService.porIdUsuario(authenticatedUserService.getId());

        Ong ong = buscarOngService.porIdOng(id);

        if (verificaVoluntarioSegueOngService.verificar(voluntario, id)) {
            unfollowOngService.unfollow(voluntario, ong);
            return;
        }

        voluntario.getOngsSeguidas().add(ong);

        voluntarioRepository.save(voluntario);
    }


    private boolean validarOngJaSeguida(Voluntario voluntario, Ong ong) {
        return voluntario.getOngsSeguidas().stream().anyMatch(o -> o.getId().equals(ong.getId()));
    }

}
