package com.example.letsgojohor.model;

public class Transport {

    private String name, description, type, photo, city;

    public Transport() {
    }


    public Transport(String name, String description, String type, String photo, String city) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.photo = photo;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
