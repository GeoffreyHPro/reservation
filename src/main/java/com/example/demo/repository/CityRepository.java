package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.City;

public interface CityRepository extends ReactiveCrudRepository<City, Integer> {

}
