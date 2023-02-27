package com.br.api.service.security;

import com.br.api.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarUsuarioAutenticadoStrategy<OUT> {

    private final List<OperacoesUsuario> strategy;

    private final AuthenticatedUserService service;


    public OUT run() {
        Usuario usuarioAutenticado = service.get();

        return strategy.stream()
                .filter(s -> usuarioAutenticado.getPermissions().stream()
                        .anyMatch(p -> p.getName().equals(s.getPermissao().getPermissao())))
                .findFirst()
                .map(service -> (OUT) service.executar(usuarioAutenticado))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }
}
