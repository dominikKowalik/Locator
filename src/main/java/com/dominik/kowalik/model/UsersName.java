package com.dominik.kowalik.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dominik on 2016-10-28.
 */
@Entity
@Service
@Scope(value = "prototype")
public class UsersName{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}