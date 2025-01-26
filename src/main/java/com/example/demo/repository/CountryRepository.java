package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.demo.model.Country;
import reactor.core.publisher.Mono;

public interface CountryRepository extends ReactiveCrudRepository<Country, Integer> {
    Mono<Country> findByCountryName(String countryName);
}
