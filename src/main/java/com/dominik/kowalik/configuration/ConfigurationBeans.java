package com.dominik.kowalik.configuration;


import com.dominik.kowalik.Helpers.PasswordGenerator;
import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.FriendsName;
import com.dominik.kowalik.model.LocationInfo;
import com.dominik.kowalik.model.User;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


/**
 * Klasa konfiguracyjna \n
 * Tworzenie Beanow i adnotacja ComponentScan która nakazuje springowi przezukać katalogi \n
 * w poszukiwaniu deklaracji beanów które będą wymagane do wstrzyknięcia \n
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.dominik.kowalik")
public class ConfigurationBeans {
    @Bean("passwordGenerator")
    public PasswordGenerator passwordGenerator() {
        return new PasswordGenerator();
    }

    @Bean("httpHeaders")
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    @Bean("account")
    @Scope("prototype")
    public Account createAccount() {
        return new Account();
    }

    @Bean("user")
    @Scope("prototype")
    public User createUser() {
        return new User();
    }

    @Bean("locationInfo")
    @Scope("prototype")
    public LocationInfo createLocationInfo() {
        return new LocationInfo();
    }

    @Bean
    @Scope("prototype")
    public List<FriendsName> friendslist() {
        return new LinkedList<>();
    }

    @Bean()
    @Scope("prototype")
    public List<User> usersList() {
        return new LinkedList<>();
    }

    @Bean("localTime")
    @Scope("prototype")
    public LocalTime getlocalTime() {
        return LocalTime.now();
    }

    @Bean("upToDateDateTime")
    @Scope("prototype")
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    @Bean("formatter HH:mm dd")
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("HH:mm dd.");
    }

}
