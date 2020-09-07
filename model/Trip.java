package com.example.letsgojohor.model;

import java.util.Date;

public class Trip {

    private String tripName, tripDescription, tripDestination;
    private String startDate, endDate;
    private String user_id;

    public Trip() {
    }

    public Trip(String tripName, String tripDescription, String tripDestination, String startDate, String endDate, String user_id) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.tripDestination = tripDestination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user_id = user_id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }




}
