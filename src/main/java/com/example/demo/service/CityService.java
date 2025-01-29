package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Mono<City> getCity(int id) {
        return cityRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    private Mono<City> getCityByName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    public Mono<Object> addCity(String cityName, int CountryId) {
        return getCityByName(cityName)
                .flatMap(existingCountry -> {
                    return Mono.error(new AlreadyCreatedException());
                })
                .switchIfEmpty(cityRepository.save(new City(cityName, CountryId)));
    }

    public Flux<City> getAllCities() {
        return cityRepository.findAll();
    }
}
