package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;

import reactor.core.publisher.Mono;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Mono<Hotel> getHotel(int id) {
        return hotelRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    private Mono<Hotel> getHotelByName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
    }

    public Mono<Object> addHotel(String hotelName, int CityId) {
        return getHotelByName(hotelName)
                .flatMap(existingCountry -> {
                    return Mono.error(new AlreadyCreatedException());
                })
                .switchIfEmpty(hotelRepository.save(new Hotel(hotelName, CityId)));
    }
}
