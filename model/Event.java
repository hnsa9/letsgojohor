package com.example.letsgojohor.model;

import java.util.Date;

public class Event {

    private String eventName, eventDescription, eventLocation, eventContact;
    private float eventPrice;
    private Date eventDate;
    private String city;

    public Event() {
    }

    public Event(String eventName, String eventDescription, String eventLocation, String eventContact, float eventPrice, Date eventDate, String city) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventContact = eventContact;
        this.eventPrice = eventPrice;
        this.eventDate = eventDate;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventContact() {
        return eventContact;
    }

    public void setEventContact(String eventContact) {
        this.eventContact = eventContact;
    }

    public float getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(float eventPrice) {
        this.eventPrice = eventPrice;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
