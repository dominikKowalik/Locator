package com.dominik.kowalik.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dominik on 2016-10-20.
 */

@Configuration
@ComponentScan({"com.dominik.kowalik.model" , "com.dominik.kowalik.web"} )
public class Context {
}
