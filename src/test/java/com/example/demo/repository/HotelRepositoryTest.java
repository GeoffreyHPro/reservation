package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import com.example.demo.model.Hotel;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testSaveHotelAndFindById() {
        Hotel hotel = new Hotel("Barriere", 0);

        Mono<Hotel> hotelSaved = hotelRepository.save(hotel);

        StepVerifier.create(hotelSaved)
                .assertNext(saved -> {
                    assertEquals("Barriere", saved.getHotelName());
                    assertNotEquals(0, saved.getCityId());
                }).verifyComplete();

        Mono<Hotel> hotelFound = hotelRepository.findById(hotel.getId());

        StepVerifier.create(hotelFound)
                .assertNext(found -> {
                    assertEquals(hotel.getHotelName(), found.getHotelName());
                    assertEquals(hotel.getCityId(), found.getCityId());
                }).verifyComplete();
    }
}
