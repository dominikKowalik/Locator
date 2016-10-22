package com.dominik.kowalik.model;

import org.springframework.stereotype.Service;

import javax.persistence.*;


@Service("locationInfo")
@Entity

public class LocationInfo {
    private double latitude;
    private double longitude;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocationInfo() {
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}