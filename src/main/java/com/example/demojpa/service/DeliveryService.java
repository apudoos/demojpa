package com.example.demojpa.service;

import com.example.demojpa.Repository.DeliveryRepository;
import com.example.demojpa.data.Delivery;
import com.example.demojpa.projection.RecipientAndPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {

        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public Delivery find(Long id) {
        return deliveryRepository.find(id);
    }

    public RecipientAndPrice getBill(Long deliveryId){
        return deliveryRepository.getBill(deliveryId);
    }



}
