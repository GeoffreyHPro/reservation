package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Hotel;

public interface HotelRepository extends ReactiveCrudRepository<Hotel, Integer> {

}
