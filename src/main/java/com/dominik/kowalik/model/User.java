package com.dominik.kowalik.model;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.*;

/**
 * Created by dominik on 2016-10-22.
 */


@Entity
@Service
@Qualifier("user")
public class User implements UserInterface {
    private String emailAdress;
    private String name;
    private String lastName;

    @OneToOne(cascade=CascadeType.ALL)
    private LocationInfo locationInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public  User(){

    }

    public User(String emailAdress, String name, String lastName, LocationInfo locationInfo) {
        this.emailAdress = emailAdress;
        this.name = name;
        this.lastName = lastName;
        this.locationInfo = locationInfo;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfoInterface locationInfo) {

    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("emailAdress='").append(emailAdress).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", locationInfo=").append(locationInfo);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
