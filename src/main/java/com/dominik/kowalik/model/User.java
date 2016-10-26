package com.dominik.kowalik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dominik on 2016-10-22.
 */

/**
 * this class represents user's state which could be visible for another users
 */

@Entity
@Service
public class User{
    private String emailAdress;
    private String username;
    private String statement;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @Autowired
    private List<User> friends;

    @OneToOne(cascade=CascadeType.ALL)
    @Autowired
    private LocationInfo locationInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String userName, String emailAdress) {
        this.username = userName;
        this.emailAdress = emailAdress;
    }

    public void updateUser(User user){
        this.emailAdress = user.getEmailAdress();
        this.friends = new ArrayList<>(user.getFriends());
        this.username = user.getUsername();
        this.locationInfo.setLatitude(user.getLocationInfo().getLatitude());
        this.locationInfo.setLongitude(user.getLocationInfo().getLongitude());
        this.statement = user.getStatement();

    }


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

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("emailAdress='").append(emailAdress).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", statement='").append(statement).append('\'');
        sb.append(", friends=").append(friends);
        sb.append(", locationInfo=").append(locationInfo);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
