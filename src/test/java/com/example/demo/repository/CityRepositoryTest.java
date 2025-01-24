package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.City;
import com.example.demo.model.Country;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    private Country country;

    @BeforeEach
    public void setUp() {
        this.country = new Country("France");
        StepVerifier.create(countryRepository.save(country))
                .assertNext(savedCountry -> {
                    this.country.setId(savedCountry.getId());
                    assertEquals("France", savedCountry.getCountryName());
                })
                .verifyComplete();
    }

    @Test
    public void testSaveCityAndFindById() {
        City city = new City("Lille", this.country.getId());

        Mono<City> citySaved = cityRepository.save(city);

        StepVerifier.create(citySaved)
                .assertNext(saved -> {
                    assertEquals("Lille", saved.getCityName());
                    assertEquals(this.country.getId(), saved.getCountryId());
                }).verifyComplete();

        Mono<City> cityFound = cityRepository.findById(city.getId());

        StepVerifier.create(cityFound)
                .assertNext(found -> {
                    assertEquals(city.getCityName(), found.getCityName());
                    assertEquals(city.getCountryId(), found.getCountryId());
                }).verifyComplete();
    }
}
