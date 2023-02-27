package com.br.api.service.ong;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Interesse;
import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.request.CriarCampanhaDataRequest;
import com.br.api.mapper.EntityCampanhaMapper;
import com.br.api.repository.CampanhaRepository;
import com.br.api.repository.OngRepository;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.service.aws.SalvarFotoService;
import com.br.api.service.interesse.ValidarInteressesService;
import com.br.api.service.security.AuthenticatedUserService;
import com.br.api.service.twilio.SendNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CriarCampanhaService {

    private final EntityCampanhaMapper entityCampanhaMapper = new EntityCampanhaMapper();

    private final VoluntarioRepository voluntarioRepository;

    private final SendNotificationService sendNotificationService;

    private final CampanhaRepository campanhaRepository;

    private final BuscarOngService buscarOngService;

    private final AuthenticatedUserService authenticatedUserService;

    private final SalvarFotoService salvarFotoService;

    private final OngRepository ongRepository;

    private final ValidarInteressesService validarInteressesService;

    @Transactional
    public void criar(CriarCampanhaDataRequest request, List<MultipartFile> fotos) throws UnsupportedEncodingException {

        Ong ong = buscarOngService.porIdUsuario(authenticatedUserService.getId());

        List<Interesse> interesses = validarInteressesService.getInteresses(request.getInteresses());

        List<String> urlFotosCampanha = salvarFotoService.salvar(fotos);

        Campanha campanha = entityCampanhaMapper.apply(request, interesses, urlFotosCampanha);

        ong.getCampanhas().add(campanha);

        campanha.setDono(ong);

        Campanha campanhaCriada = campanhaRepository.save(campanha);
        ongRepository.save(ong);

        List<Voluntario> voluntarioInteressados = voluntarioRepository.findVoluntarioInteressados(campanhaCriada.getId(), ong.getId());
        sendNotificationService.send(campanhaCriada, voluntarioInteressados);
    }
}
