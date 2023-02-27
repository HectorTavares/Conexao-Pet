package com.br.api.service.usuario;

import com.br.api.domain.model.Usuario;
import com.br.api.domain.request.AtualizarUsuarioRequest;
import com.br.api.mapper.EntityUpdateUsuarioMapper;
import com.br.api.repository.UsuarioRepository;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.service.aws.SalvarFotoService;
import com.br.api.service.security.AuthenticatedUserService;
import com.br.api.service.voluntario.BuscarVoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AtualizarUsuarioService {

    private final EntityUpdateUsuarioMapper mapper = new EntityUpdateUsuarioMapper();

    private final BuscarVoluntarioService buscarVoluntarioService;

    private final VoluntarioRepository voluntarioRepository;

    private final UsuarioRepository usuarioRepository;

    private final AuthenticatedUserService authenticatedUserService;

    private final SalvarFotoService salvarFotoService;

    public void atualizar(AtualizarUsuarioRequest request, MultipartFile foto) {
        Usuario authenticatedUser = authenticatedUserService.get();

        String fotoParaSalvar;

        if (isNull(foto)) {
            fotoParaSalvar = authenticatedUser.getFoto();
        } else {
            fotoParaSalvar = salvarFotoService.salvar(foto);
        }

        Usuario updatedUser = mapper.apply(authenticatedUser, request, fotoParaSalvar);


        usuarioRepository.save(updatedUser);
    }

}
