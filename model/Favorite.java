package com.example.letsgojohor.model;

public class Favorite {

    private String favorite_id, user_id;
    private String favorite_name;
    private String favorite_photo;

    public Favorite(String favorite_id, String user_id, String favorite_name, String favorite_photo) {
        this.favorite_id = favorite_id;
        this.user_id = user_id;
        this.favorite_name = favorite_name;
        this.favorite_photo = favorite_photo;
    }

    public String getFavorite_photo() {
        return favorite_photo;
    }

    public void setFavorite_photo(String favorite_photo) {
        this.favorite_photo = favorite_photo;
    }

    public String getFavorite_name() {
        return favorite_name;
    }

    public void setFavorite_name(String favorite_name) {
        this.favorite_name = favorite_name;
    }

    public Favorite() {
    }



    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
