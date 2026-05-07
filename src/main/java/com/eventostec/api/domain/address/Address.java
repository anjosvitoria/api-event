package com.eventostec.api.domain.address;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name="address")
@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;

    private String uf;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
