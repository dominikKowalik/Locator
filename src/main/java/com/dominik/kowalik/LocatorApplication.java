package com.dominik.kowalik;

import com.dominik.kowalik.Helpers.Logger;
import com.dominik.kowalik.Helpers.PasswordGenerator;
import com.dominik.kowalik.web.SMTPMailSender;
import com.sun.deploy.ref.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;

@SpringBootApplication
public class LocatorApplication {


    public static void main(String[] args) {
        SpringApplication.run(LocatorApplication.class, args);
    }
}

