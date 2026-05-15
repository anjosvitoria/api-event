package com.eventostec.api.controller;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDTO;
import com.eventostec.api.domain.event.EventResponseDto;
import com.eventostec.api.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getEvents(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<EventResponseDto> events = eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDto>> getFilteredEvents(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(required = false) String title,
                                                                    @RequestParam String city,
                                                                    @RequestParam String uf,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<EventResponseDto> events = eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok(events);
    }

}
