package com.example.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.demo.model.Country;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryRepository extends ReactiveCrudRepository<Country, Integer> {
    Mono<Country> findByCountryName(String countryName);

    @Query("SELECT * FROM country WHERE LOWER(countryName) LIKE LOWER(CONCAT('%',:name, '%')) LIMIT :limit OFFSET :offset")
    Flux<Country> findAllPaged(String name, int limit, int offset);
}
