package com.br.api.service.ong;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Usuario;
import com.br.api.domain.response.OngModelResponse;
import com.br.api.mapper.ResponseOngMapper;
import com.br.api.service.security.OperacoesUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarOngAutenticadaService implements OperacoesUsuario<OngModelResponse> {

    private final BuscarOngService buscarOngService;

    private final ResponseOngMapper mapper = new ResponseOngMapper();


    @Override
    public Permissao getPermissao() {
        return Permissao.ONG;
    }

    @Override
    public OngModelResponse executar(Usuario usuario) {
        Ong entity = buscarOngService.porIdUsuario(usuario.getId());

        return mapper.apply(entity);
    }
}
