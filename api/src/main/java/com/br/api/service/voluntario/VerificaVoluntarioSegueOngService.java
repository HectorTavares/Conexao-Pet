package com.br.api.service.voluntario;

import com.br.api.domain.model.Voluntario;
import org.springframework.stereotype.Service;

@Service
public class VerificaVoluntarioSegueOngService {


    public boolean verificar(Voluntario voluntario, Long idOng) {
        return voluntario.getOngsSeguidas().stream().anyMatch(os -> os.getId().equals(idOng));
    }

}
