package com.example.demo.request;

import lombok.Data;

@Data
public class HotelRequest {
    private String hotelName;
    private int cityId;

    public HotelRequest() {
    }

    public HotelRequest(String hotelName, int cityId) {
        this.hotelName = hotelName;
        this.cityId = cityId;
    }
}
