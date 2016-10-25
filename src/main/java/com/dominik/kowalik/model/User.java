package com.dominik.kowalik.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

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
    private String statement;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<User> friends;

    @OneToOne(cascade=CascadeType.ALL)
    private LocationInfo locationInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    public static float distFrom(User user1, User user2) {
//        double earthRadius = 6371000; //meters
//        double dLat = Math.toRadians(user2.getLocationInfo().getLatitude()-user1.getLocationInfo().getLatitude());
//        double dLng = Math.toRadians(user2.getLocationInfo().getLongitude()-user2.getLocationInfo().getLongitude());
//        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
//                Math.cos(Math.toRadians(user1.getLocationInfo().getLatitude())) *
//                        Math.cos(Math.toRadians(user2.getLocationInfo().getLongitude())) *
//                        Math.sin(dLng/2) * Math.sin(dLng/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        float dist = (float) (earthRadius * c);
//        return dist;
//    }



    public  User(){
    }

    public User(String emailAdress, String name, String lastName, boolean exists, String statement, LocationInfo locationInfo){
        this.emailAdress = emailAdress;
        this.name = name;
        this.lastName = lastName;
        this.statement = statement;
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

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

// TODO
//    public boolean isExists() {
//        return exists;
//    }
//
//    public void setExists(boolean exists) {
//        this.exists = exists;
//    }
}
