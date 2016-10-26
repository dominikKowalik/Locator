package com.dominik.kowalik.web;


import com.dominik.kowalik.DAL.AccountDao;
import com.dominik.kowalik.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Created by dominik on 2016-10-26.
 */

@RestController
@RequestMapping("register")
public class RegistrationController {

    @Autowired
    AccountDao accountDao;


    @PostMapping("{username}/{password}/{email}")
    public String registerUser(@PathVariable("username") String username, @PathVariable("password")
    String password, @PathVariable("email") String email){
        accountDao.save(new Account(username, password, email));

        return (new StringBuilder("redirect:/user/").append(username).
                append("/").append(password).append("/").append(email)).toString();
    }




}

