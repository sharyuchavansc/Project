package com.example.foodiesta.model;

import java.io.Serializable;

public class Restaurent implements Serializable {
    int restaurentId;
    String restaurentName;
    String restaurentAddress;
    String restaurentEmailId;
    String restaurentMobileNo;

    public Restaurent()
    {

    }

    public Restaurent(int restaurentId, String restaurentName, String restaurentAddress, String restaurentEmailId, String restaurentMobileNo) {
        this.restaurentId = restaurentId;
        this.restaurentName = restaurentName;
        this.restaurentAddress = restaurentAddress;
        this.restaurentEmailId = restaurentEmailId;
        this.restaurentMobileNo = restaurentMobileNo;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public String getRestaurentName() {
        return restaurentName;
    }

    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
    }

    public String getRestaurentAddress() {
        return restaurentAddress;
    }

    public void setRestaurentAddress(String restaurentAddress) {
        this.restaurentAddress = restaurentAddress;
    }

    public String getRestaurentEmailId() {
        return restaurentEmailId;
    }

    public void setRestaurentEmailId(String restaurentEmailId) {
        this.restaurentEmailId = restaurentEmailId;
    }

    public String getRestaurentMobileNo() {
        return restaurentMobileNo;
    }

    public void setRestaurentMobileNo(String restaurentMobileNo) {
        this.restaurentMobileNo = restaurentMobileNo;
    }

}
