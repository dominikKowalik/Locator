package com.dominik.kowalik.Helpers;

import org.slf4j.LoggerFactory;

/**
 * Created by dominik on 2016-11-10.
 */
public class Logger {
    /**
     * Pobranie loggera używanego w aplikacji
      */
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger("**************INFO**************");
    public static void log(String info) {
        logger.info(info);
    }
}
