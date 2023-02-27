package com.br.api.service.security;

import com.br.api.domain.model.Usuario;
import com.br.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class FindUserSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario user = userRepository.findByEmail(email);
        return new UserSecurity(user);
    }
}

