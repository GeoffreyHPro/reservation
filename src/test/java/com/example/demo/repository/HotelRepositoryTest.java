package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.model.Hotel;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    private City city;

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

        this.city = new City("Lille", this.country.getId());

        Mono<City> citySaved = cityRepository.save(city);

        StepVerifier.create(citySaved)
                .assertNext(saved -> {
                    assertEquals("Lille", saved.getCityName());
                    assertEquals(this.country.getId(), saved.getCountryId());
                }).verifyComplete();

    }

    @Test
    public void testSaveHotelAndFindById() {
        Hotel hotel = new Hotel("Barriere", this.city.getId());

        Mono<Hotel> hotelSaved = hotelRepository.save(hotel);

        StepVerifier.create(hotelSaved)
                .assertNext(saved -> {
                    assertEquals("Barriere", saved.getHotelName());
                }).verifyComplete();

        Mono<Hotel> hotelFound = hotelRepository.findById(hotel.getId());

        StepVerifier.create(hotelFound)
                .assertNext(found -> {
                    assertEquals(hotel.getHotelName(), found.getHotelName());
                    assertEquals(hotel.getCityId(), found.getCityId());
                }).verifyComplete();
    }
}
