package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bedroom")
public class Bedroom {
    @Id
    private int id;

    @Column("nbMaxPeople")
    private int nbMaxPeople;

    @Column("bedroomName")
    private String bedroomName;

    @Column("hotelId")
    private int hotelId;

    public Bedroom() {
    }

    public Bedroom(int nbMaxPeople, String bedroomName, int hotelId) {
        this.nbMaxPeople = nbMaxPeople;
        this.bedroomName = bedroomName;
        this.hotelId = hotelId;
    }

    public void setBedroomName(String bedroomName) {
        this.bedroomName = bedroomName;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setNbMaxPeople(int nbMaxPeople) {
        this.nbMaxPeople = nbMaxPeople;
    }

    public String getBedroomName() {
        return bedroomName;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getId() {
        return id;
    }

    public int getNbMaxPeople() {
        return nbMaxPeople;
    }
}