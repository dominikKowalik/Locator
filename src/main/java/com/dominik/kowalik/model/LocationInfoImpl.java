package com.dominik.kowalik.model;

/**
 * Created by dominik on 2016-10-19.
 */

import org.springframework.stereotype.Service;

/**
 * Basic implementation of LocationInfo that helps exchange data with rest api
 */

@Service("locationInfoImpl")
public class LocationInfoImpl implements LocationInfo {
    public String latitude;

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
