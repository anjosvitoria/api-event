package com.eventostec.api.services;

import com.eventostec.api.domain.coupon.Coupon;
import com.eventostec.api.domain.coupon.CouponRequestDTO;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.repositories.CouponRepository;
import com.eventostec.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventID, CouponRequestDTO couponRequestDTO) {
        Event event = eventRepository.findById(eventID).orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Coupon coupon = new Coupon();
        coupon.setEvent(event);
        coupon.setDiscount(couponRequestDTO.discount());
        coupon.setValid(couponRequestDTO.valid());
        return couponRepository.save(coupon);
    }
}
