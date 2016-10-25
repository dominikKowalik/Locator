package com.dominik.kowalik.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
}
