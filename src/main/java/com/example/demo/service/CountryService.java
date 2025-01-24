package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;

import reactor.core.publisher.Mono;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Mono<Country> getCountry(int id) {
        return countryRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException()));
    }
}
