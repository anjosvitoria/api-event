package com.eventostec.api.domain.event;


public record EventRequestDTO(String title, String description, Long date, String city, String state, Boolean remote, String eventUrl, String imageBase64, String imageName) {
}
