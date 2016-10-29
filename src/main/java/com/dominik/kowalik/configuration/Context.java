package com.dominik.kowalik.configuration;


import com.dominik.kowalik.model.Account;
import com.dominik.kowalik.model.FriendsName;
import com.dominik.kowalik.model.LocationInfo;
import com.dominik.kowalik.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

/**
 * Created by dominik on 2016-10-20.
 */

@Configuration
@ComponentScan("com.dominik.kowalik")
public class Context{

    @Bean("httpHeaders")
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }

    @Bean("account")
    @Scope("prototype")
    public Account createAccount(){
        return new Account();
    }

    @Bean("user")
    @Scope("prototype")
    public User createUser(){return new User();}

    @Bean("locationInfo")
    @Scope("prototype")
    public LocationInfo createLocationInfo(){return new LocationInfo();}

    @Bean
    @Scope("prototype")
    public FriendsName createUseresName(){return new FriendsName();}
}
