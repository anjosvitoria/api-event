package com.eventostec.api.controller;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDTO;
import com.eventostec.api.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        System.out.println("Recebendo imagem Base64: " + (eventRequestDTO.imageBase64() != null ? "Sim" : "Não"));
        System.out.println("Nome da imagem: " + eventRequestDTO.imageName());

        Event newEvent = eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }
}
