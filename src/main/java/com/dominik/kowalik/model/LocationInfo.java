package com.dominik.kowalik.model;

import org.springframework.stereotype.Service;

@Service("locationInfo")
public class LocationInfo{
    double latitude;
    double longitude;

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

    public LocationInfo(){}

    @Override
    public String toString() {
        return "LocationInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}