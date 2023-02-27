package com.br.api.service.voluntario;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Usuario;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.response.VoluntarioModelReponse;
import com.br.api.mapper.ResponseVoluntarioMapper;
import com.br.api.service.security.OperacoesUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarVoluntarioAutenticadoService implements OperacoesUsuario<VoluntarioModelReponse> {

    private final BuscarVoluntarioService buscarVoluntarioService;

    private final ResponseVoluntarioMapper mapper = new ResponseVoluntarioMapper();


    @Override
    public Permissao getPermissao() {
        return Permissao.VOLUNTARIO;
    }

    @Override
    public VoluntarioModelReponse executar(Usuario usuario) {
        Voluntario entity = buscarVoluntarioService.porIdUsuario(usuario.getId());

        return mapper.apply(entity);
    }
}
