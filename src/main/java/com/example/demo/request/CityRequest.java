package com.example.demo.request;

import lombok.Data;

@Data
public class CityRequest {
    private String cityName;
    private int CountryId;

    public CityRequest() {
    }

    public CityRequest(String cityName, int CountryId) {
        this.cityName = cityName;
        this.CountryId = CountryId;
    }
}
