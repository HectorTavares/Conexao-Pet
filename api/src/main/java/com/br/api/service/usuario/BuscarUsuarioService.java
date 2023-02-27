
package com.br.api.service.usuario;

import com.br.api.domain.request.BuscarUsuariosEAnimaisResponse;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.domain.response.BuscaUsuarioResponse;
import com.br.api.mapper.ResponseBuscarUsuarioMapper;
import com.br.api.mapper.ResponseBuscarUsuariosEAnimaisMapper;
import com.br.api.repository.UsuarioRepository;
import com.br.api.service.animal.BuscarAnimalService;
import com.br.api.service.security.AuthenticatedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarUsuarioService {

    private final ResponseBuscarUsuarioMapper mapper = new ResponseBuscarUsuarioMapper();

    private final ResponseBuscarUsuariosEAnimaisMapper responseBuscarUsuariosEAnimaisMapper = new ResponseBuscarUsuariosEAnimaisMapper();

    private final UsuarioRepository usuarioRepository;

    private final AuthenticatedUserService authenticatedUserService;

    private final BuscarAnimalService buscarAnimalService;

    private static final String VOLUNTARIO_PERMISSAO = "Voluntário";

    private static final String ONG_PERMISSAO = "Ong";

    public BuscarUsuariosEAnimaisResponse buscar(String textToSearch) {
        Long loggedUserId = authenticatedUserService.getId();

        List<BuscaUsuarioResponse> voluntarios = usuarioRepository.buscarUsuariosPorNomeEPermissao(textToSearch, loggedUserId, VOLUNTARIO_PERMISSAO).stream().map(mapper).collect(Collectors.toList());

        List<BuscaUsuarioResponse> ongs = usuarioRepository.buscarUsuariosPorNomeEPermissao(textToSearch, loggedUserId, ONG_PERMISSAO).stream().map(mapper).collect(Collectors.toList());

        List<AnimalModelResponse> animais = buscarAnimalService.buscar(textToSearch);

        return responseBuscarUsuariosEAnimaisMapper.apply(voluntarios, ongs, animais);
    }

}
