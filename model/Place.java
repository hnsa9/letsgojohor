package com.example.letsgojohor.model;

public class Place {

    private String placeAddress, placeContact, placeDescription, placeName, placePhoto, city;

    public Place() {
    }

    public Place(String placeAddress, String placeContact, String placeDescription, String placeName, String placePhoto, String city) {
        this.placeAddress = placeAddress;
        this.placeContact = placeContact;
        this.placeDescription = placeDescription;
        this.placeName = placeName;
        this.placePhoto = placePhoto;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceContact() {
        return placeContact;
    }

    public void setPlaceContact(String placeContact) {
        this.placeContact = placeContact;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }
}
