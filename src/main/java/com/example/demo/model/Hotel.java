package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("hotel")
public class Hotel {

    @Id
    private int id;

    @Column("hotelName")
    private String hotelName;

    @Column("cityId")
    private int cityId;

    public Hotel(String hotelName, int cityId) {
        this.hotelName = hotelName;
        this.cityId = cityId;
    }

    public Hotel() {

    }

    public int getCityId() {
        return cityId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getId() {
        return id;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
