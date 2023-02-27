package com.br.api.service.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalvarFotoService {

    private final AwsService service;

    public String salvar(MultipartFile file) {
        return service.uploadFile(file);
    }

    public List<String> salvar(List<MultipartFile> file) {
        return file.stream().map(service::uploadFile).collect(Collectors.toList());
    }
}
