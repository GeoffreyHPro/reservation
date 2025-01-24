package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountryTest {

    private Country country;
    private Country countryConstructor;

    @BeforeEach
    public void setUp() {
        this.country = new Country();
        this.countryConstructor = new Country("France");
    }

    @Test
    public void testCountryConstructor() {
        assertEquals("France", this.countryConstructor.getCountryName());
    }

    @Test
    public void testCountryParameters() {
        this.country.setCountryName("France");
        assertEquals("France", this.country.getCountryName());
        this.country.setCountryName("England");
        assertEquals("England", this.country.getCountryName());
    }
}
