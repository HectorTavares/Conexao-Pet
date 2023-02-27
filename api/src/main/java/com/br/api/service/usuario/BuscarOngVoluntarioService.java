package com.br.api.service.usuario;

import com.br.api.domain.constants.ExceptionsMessage;
import com.br.api.domain.model.Usuario;
import com.br.api.domain.response.OngVoluntarioModelResponse;
import com.br.api.mapper.ResponseOngVoluntarioModelMapper;
import com.br.api.repository.UsuarioRepository;
import com.br.api.service.ong.BuscarOngService;
import com.br.api.service.voluntario.BuscarVoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BuscarOngVoluntarioService {

    private final BuscarOngService buscarOngService;

    private final BuscarVoluntarioService buscarVoluntarioService;

    private final UsuarioRepository usuarioRepository;

    private final ResponseOngVoluntarioModelMapper mapper = new ResponseOngVoluntarioModelMapper();


    public OngVoluntarioModelResponse buscar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionsMessage.USUARIO_NAO_ENCONTRADO));

        Long idUsuario = usuario.getId();

        String permissionName = usuario.getPermissions().get(0).getName();

        return permissionName.equals("Voluntário")
                ? mapper.apply(null, buscarVoluntarioService.porIdUsuario(idUsuario))
                : mapper.apply(buscarOngService.porIdUsuario(idUsuario), null);
    }

}
