package com.br.api.service.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class AwsService {

    private static final String FILE_URL = "https://hv-tcc-bucket.s3.sa-east-1.amazonaws.com/";

    private static final String[] FILE_TYPES = {"jpeg", "jpg", "png"};

    @Value("${aws.bucket-name}")
    private String bucketName;

    private final AmazonS3 aws3Client;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);

        fileTypeValidation(file.getOriginalFilename());

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        aws3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        boolean delete = fileObj.delete();

        if (!delete) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel deletar o arquivo");
        }
        return FILE_URL + fileName;
    }

    private void fileTypeValidation(String fileName) {
        String extension = "";
        int lastFileExtension = fileName.lastIndexOf('.');
        if (lastFileExtension >= 0) {
            extension = fileName.substring(lastFileExtension + 1).toLowerCase();
        }
        if (!Arrays.asList(FILE_TYPES).contains(extension)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de arquivo inválido");
        }
    }

    public void deleteFile(String fileName) {
        aws3Client.deleteObject(bucketName, fileName);
    }

    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel converter o arquivo");
        }
        return convertedFile;
    }
}
