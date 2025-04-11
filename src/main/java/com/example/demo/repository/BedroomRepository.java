package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Bedroom;

import reactor.core.publisher.Mono;

public interface BedroomRepository extends ReactiveCrudRepository<Bedroom, Integer> {
    Mono<Bedroom> getBedroomByName(String name);
}