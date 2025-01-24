package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyCreatedException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Mono<Country> getCountry(int id) {
        return countryRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }

    private Mono<Country> getCountryByName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    public Mono<Object> addCountry(String countryName) {
        return getCountryByName(countryName)
                .flatMap(existingCountry -> {
                    return Mono.error(new AlreadyCreatedException());
                })
                .switchIfEmpty(countryRepository.save(new Country(countryName)));
    }

    public Flux<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
