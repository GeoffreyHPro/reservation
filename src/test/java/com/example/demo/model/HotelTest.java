package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelTest {

    private Hotel hotel;
    private Hotel hotelConstructor;

    @BeforeEach
    public void setUp() {
        this.hotel = new Hotel();
        this.hotelConstructor = new Hotel("Barriere", 10);
    }

    @Test
    public void testHotelConstructor() {
        assertEquals("Barriere", this.hotelConstructor.getHotelName());
        assertEquals(10, this.hotelConstructor.getCityId());
    }

    @Test
    public void testCountryParameters() {
        this.hotel.setHotelName("Barriere");
        assertEquals("Barriere", this.hotel.getHotelName());
        this.hotel.setCityId(1);
        assertEquals(1, this.hotel.getCityId());
    }
    
}
