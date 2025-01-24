package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityTest {
    private City city;

    @BeforeEach
    public void setUp() {
        this.city = new City();
    }

    @Test
    public void testCityParameters() {
        this.city.setCityName("Lille");
        assertEquals("Lille", this.city.getCityName());
        this.city.setCountryId(1);
        assertEquals(1, this.city.getCountryId());
    }
}
