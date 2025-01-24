package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("city")
public class City {
    @Id
    private int id;

    @Column("cityName")
    private String cityName;

    @Column("countryId")
    private int countryId;

    public City(String cityName, int countryId) {
        this.cityName = cityName;
        this.countryId = countryId;
    }

    public City() {

    }

    public String getCityName() {
        return cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public int getId() {
        return id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
