package com.br.api.service.ong;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Ong;
import com.br.api.domain.request.CadastrarOngRequest;
import com.br.api.mapper.EntityUsuarioMapper;
import com.br.api.repository.OngRepository;
import com.br.api.util.PermissionFactory;
import com.br.api.validator.ValidarRequestCadastroUsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastrarOngService {

    private final OngRepository repository;

    private final ValidarRequestCadastroUsuarioValidator validator;

    private final PasswordEncoder passwordEncoder;

    private final PermissionFactory permissionFactory;

    private final EntityUsuarioMapper mapper = new EntityUsuarioMapper();

    public void cadastrar(CadastrarOngRequest request) {

        validator.accept(request.getUsuario());

        Ong entity = buildOng(request);

        entity.getUsuario().setSenha(passwordEncoder.encode(request.getUsuario().getSenha()));
        entity.getUsuario().setPermissions(new ArrayList<>(List.of(permissionFactory.make(Permissao.ONG))));

        repository.save(entity);
    }

    private Ong buildOng(CadastrarOngRequest request) {
        return Ong.builder()
                .usuario(mapper.apply(request.getUsuario()))
                .build();
    }
}
