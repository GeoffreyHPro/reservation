package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.City;

import reactor.core.publisher.Mono;

public interface CityRepository extends ReactiveCrudRepository<City, Integer> {
    Mono<City> findByCityName(String cityName);
}
