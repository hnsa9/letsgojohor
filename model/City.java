package com.example.letsgojohor.model;

public class City {
    private String cityName;
    private String cityInfo;
    private String cityPhoto;

    public String getCityPhoto() {
        return cityPhoto;
    }

    public void setCityPhoto(String cityPhoto) {
        this.cityPhoto = cityPhoto;
    }



    public City() {
    }

    public City(String cityName, String cityInfo, String cityPhoto) {
        this.cityName = cityName;
        this.cityInfo = cityInfo;
        this.cityPhoto = cityPhoto;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }
}
