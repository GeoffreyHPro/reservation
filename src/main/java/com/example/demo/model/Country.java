package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("country")
public class Country {
    @Id
    private int id;

    @Column("countryName")
    private String countryName;

    public Country() {

    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getId() {
        return id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
