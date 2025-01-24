package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import com.example.demo.model.City;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testSaveCityAndFindById() {
        City city = new City("Lille", 1);

        Mono<City> citySaved = cityRepository.save(city);

        StepVerifier.create(citySaved)
                .assertNext(saved -> {
                    assertEquals("Lille", saved.getCityName());
                    assertEquals(1, saved.getCountryId());
                    assertNotEquals(0, saved.getId());
                }).verifyComplete();

        Mono<City> cityFound = cityRepository.findById(city.getId());

        StepVerifier.create(cityFound)
                .assertNext(found -> {
                    assertEquals(city.getCityName(), found.getCityName());
                    assertEquals(city.getCountryId(), found.getCountryId());
                }).verifyComplete();

    }
}
