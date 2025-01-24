package com.example.demo.request;

import lombok.Data;

@Data
public class CountryRequest {
    private String countryName;

    public CountryRequest() {
    }

    public CountryRequest(String countryName) {
        this.countryName = countryName;
    }
}
