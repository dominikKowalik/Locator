package com.dominik.kowalik.model;

/**
 * Created by dominik on 2016-10-22.
 */
public interface UserInterface{
    public String getEmailAdress();

    public void setEmailAdress(String emailAdress);

    public String getName();

    public void setName(String name);

    public String getLastName();

    public void setLastName(String lastName);

    public LocationInfo getLocationInfo();

    public void setLocationInfo(LocationInfo locationInfo);
//
//    public boolean isExists();
//
//    public void setExists(boolean exists);
}
