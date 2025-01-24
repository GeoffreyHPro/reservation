package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelTest {

    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        this.hotel = new Hotel();
    }

    @Test
    public void testCountryParameters() {
        this.hotel.setHotelName("Barriere");
        assertEquals("Barriere", this.hotel.getHotelName());
        this.hotel.setCountryId(1);
        assertEquals(1, this.hotel.getCountryId());
    }
    
}
