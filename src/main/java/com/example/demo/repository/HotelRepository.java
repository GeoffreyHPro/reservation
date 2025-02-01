package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Hotel;

import reactor.core.publisher.Mono;

public interface HotelRepository extends ReactiveCrudRepository<Hotel, Integer> {
    Mono<Hotel> findByHotelName(String hotelName);
}
