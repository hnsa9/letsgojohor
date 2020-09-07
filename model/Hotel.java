package com.example.letsgojohor.model;

public class Hotel {

    private String hotelName;
    private String hotelAddress;
    private String hotelContact;
    private String hotelDescription;
    private String hotelAmenities;
    private double hotelPrice;
    private String room;
    private String city;

    public Hotel() {

    }

    public Hotel(String hotelName, String hotelAddress, String hotelContact, String hotelDescription, String hotelAmenities, double hotelPrice, String room, String city) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelContact = hotelContact;
        this.hotelDescription = hotelDescription;
        this.hotelAmenities = hotelAmenities;
        this.hotelPrice = hotelPrice;
        this.room = room;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(String hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public double getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(double hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }




}
