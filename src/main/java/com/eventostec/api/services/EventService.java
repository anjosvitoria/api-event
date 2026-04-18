package com.eventostec.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDTO;
import com.eventostec.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = "url-padrao-da-imagem"; // Valor padrão para imgUrl

        if(data.imageBase64() != null && data.imageName() != null){
            imgUrl = uploadImgBase64(data.imageBase64(), data.imageName());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return newEvent;
    }

    public String uploadImgBase64(String base64Image, String fileName) {
        try {
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Image);
            File tempFile = File.createTempFile("upload", fileName);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(decodedBytes);
            }
            s3Client.putObject(bucketName, fileName, tempFile);
            if (!tempFile.delete()) {
                System.out.println("Falha ao excluir o arquivo temporário:: " + tempFile.getAbsolutePath());
            }
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            System.out.println("Erro ao processar a imagem Base64: " + e.getMessage());
            return null;
        }
    }
}
