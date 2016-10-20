package com.dominik.kowalik.web;

import com.dominik.kowalik.model.LocationInfo;
import com.dominik.kowalik.model.LocationInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dominik on 2016-10-19.
 */

@RestController
public class DataExchangeRestContoller{

    @Autowired
    LocationInfoImpl locationInfoImpl;
    int i = 0 ;

    @RequestMapping("/getLocationInfo")
    public LocationInfoImpl getLocationInfo(){
        locationInfoImpl.setLatitude(i++ + "");
        return locationInfoImpl;
    }
}
