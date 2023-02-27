package com.br.api.service.voluntario;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.request.CadastrarVoluntarioRequest;
import com.br.api.mapper.EntityUsuarioMapper;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.util.PermissionFactory;
import com.br.api.validator.ValidarRequestCadastroVoluntarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastrarVoluntarioService {

    private final VoluntarioRepository repository;

    private final ValidarRequestCadastroVoluntarioValidator validator;

    private final PasswordEncoder passwordEncoder;

    private final PermissionFactory permissionFactory;

    private final EntityUsuarioMapper mapper = new EntityUsuarioMapper();

    public void cadastrar(CadastrarVoluntarioRequest request) {

        validator.accept(request);

        Voluntario entity = buildVoluntario(request);

        entity.getUsuario().setSenha(passwordEncoder.encode(request.getUsuario().getSenha()));
        entity.getUsuario().setPermissions(new ArrayList<>(List.of(permissionFactory.make(Permissao.VOLUNTARIO))));

        repository.save(entity);
    }

    private Voluntario buildVoluntario(CadastrarVoluntarioRequest request) {
        return Voluntario.builder()
                .usuario(mapper.apply(request.getUsuario()))
                .cpf(request.getCpf())
                .build();
    }
}
