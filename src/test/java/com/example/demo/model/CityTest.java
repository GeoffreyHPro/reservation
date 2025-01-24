package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityTest {
    private City city;
    private City cityConstructor;

    @BeforeEach
    public void setUp() {
        this.city = new City();
        this.cityConstructor = new City("Paris", 2);
    }

    @Test
    public void testCityConstructor() {
        assertEquals("Paris", this.cityConstructor.getCityName());
        assertEquals(2, this.cityConstructor.getCountryId());
    }

    @Test
    public void testCityParameters() {
        this.city.setCityName("Lille");
        assertEquals("Lille", this.city.getCityName());
        this.city.setCountryId(1);
        assertEquals(1, this.city.getCountryId());
    }
}
