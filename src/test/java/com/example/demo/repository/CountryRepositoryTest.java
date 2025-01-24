package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import com.example.demo.model.Country;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testSaveCountryAndFindById() {
        Country country = new Country("France");

        Mono<Country> countrySaved = countryRepository.save(country);

        StepVerifier.create(countrySaved)
                .assertNext(saved -> {
                    assertEquals("France", saved.getCountryName());
                }).verifyComplete();

        Mono<Country> countryFound = countryRepository.findById(country.getId());

        StepVerifier.create(countryFound)
                .assertNext(found -> {
                    assertEquals(country.getCountryName(), found.getCountryName());
                }).verifyComplete();
    }

}
