package com.example.letsgojohor.model;

public class Activity {

    String activityAddress;
    String activityContact;
    String activityDescription;
    String activityName;
    String activityPhoto;
    String city;

    public Activity(String activityAddress, String activityContact, String activityDescription, String activityName, String activityPhoto, String city) {
        this.activityAddress = activityAddress;
        this.activityContact = activityContact;
        this.activityDescription = activityDescription;
        this.activityName = activityName;
        this.activityPhoto = activityPhoto;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Activity() {}



    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityContact() {
        return activityContact;
    }

    public void setActivityContact(String activityContact) {
        this.activityContact = activityContact;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPhoto() {
        return activityPhoto;
    }

    public void setActivityPhoto(String activityPhoto) {
        this.activityPhoto = activityPhoto;
    }
}
