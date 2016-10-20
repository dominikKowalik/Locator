package com.dominik.kowalik.web;

import com.dominik.kowalik.model.LocationInfo;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dominik on 2016-10-19.
 */

@RestController
@RequestMapping("Locator")
public class DataExchangeRestContoller {
    private final Logger logger = LoggerFactory.getLogger( "***********INFO***********");

    @Autowired
    LocationInfo locationInfo;

    @GetMapping("/getLocation")
    public LocationInfo getLocationInfo() {
         logger.info("Locator/getLocation");
        return locationInfo;
    }

    @PostMapping("/setLocation")
    public void setLocationInfo(@RequestBody LocationInfo locationInfo) {
        logger.info("Locator/setLocation");
        this.locationInfo = locationInfo;
        logger.info(locationInfo.toString());
    }
}
