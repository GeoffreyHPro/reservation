package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Country;

public interface CountryRepository extends ReactiveCrudRepository<Country, Integer> {

}
