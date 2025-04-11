package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.model.Bedroom;
import com.example.demo.repository.BedroomRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BedroomService {

    @Autowired
    private BedroomRepository bedroomRepository;

    public Flux<Bedroom> getAllBedroom() {
        return bedroomRepository.findAll();
    }

    public Mono<Object> addBedroom(String bedroomName, int nbMaxPeople, int hotelId) {
        return bedroomRepository.getBedroomByName(bedroomName)
                .flatMap(existingCountry -> {
                    return Mono.error(new AlreadyCreatedException());
                })
                .switchIfEmpty(bedroomRepository.save(new Bedroom(nbMaxPeople, bedroomName, hotelId)));
    }
}
