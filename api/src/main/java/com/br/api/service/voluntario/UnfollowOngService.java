package com.br.api.service.voluntario;

import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Voluntario;
import com.br.api.repository.VoluntarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnfollowOngService {

    private final VoluntarioRepository voluntarioRepository;

    public void unfollow(Voluntario voluntario, Ong ong) {

        voluntario.setOngsSeguidas(voluntario.getOngsSeguidas().stream()
                .filter(os -> !Objects.equals(os.getId(), ong.getId())).collect(Collectors.toList()));

        voluntarioRepository.save(voluntario);
    }

}
