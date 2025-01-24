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

    @Column("countryId")
    private int countryId;

    public Hotel(String hotelName, int countryId) {
        this.hotelName = hotelName;
        this.countryId = countryId;
    }

    public Hotel() {

    }

    public int getCountryId() {
        return countryId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getId() {
        return id;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
