package com.example.demojpa.controller;

import com.example.demojpa.data.Delivery;
import com.example.demojpa.projection.RecipientAndPrice;
import com.example.demojpa.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping("/{deliveryId}")
    public Delivery getDeliveryById(@PathVariable Long deliveryId) {
        return deliveryService.find(deliveryId);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }

}
