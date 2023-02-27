package com.br.api.service.ong;

import com.br.api.domain.model.Animal;
import com.br.api.domain.model.Ong;
import com.br.api.domain.request.CadastrarAnimalDataRequest;
import com.br.api.mapper.EntityAnimalMapper;
import com.br.api.repository.OngRepository;
import com.br.api.service.aws.SalvarFotoService;
import com.br.api.service.security.AuthenticatedUserService;
import com.br.api.validator.ValidarRequestCadastrarAnimalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastrarAnimalService {


    private final EntityAnimalMapper entityAnimalMapper = new EntityAnimalMapper();

    private final OngRepository ongRepository;

    private final BuscarOngService buscarOngService;

    private final AuthenticatedUserService authenticatedUserService;

    private final SalvarFotoService salvarFotoService;

    private final ValidarRequestCadastrarAnimalValidator validator;

    @Transactional
    public void cadastrar(CadastrarAnimalDataRequest request, List<MultipartFile> fotos) {
        Ong ong = buscarOngService.porIdUsuario(authenticatedUserService.getId());

        validator.accept(request);

        List<String> fotosAnimalSalvas = salvarFotoService.salvar(fotos);

        Animal animal = entityAnimalMapper.apply(request, fotosAnimalSalvas);

        ong.getAnimais().add(animal);

        animal.setDono(ong);

        ongRepository.save(ong);
    }
}
