package com.example.letsgojohor.model;

public class Tip {

    private String category, tipName, tipDescription;

    public Tip() {
    }

    public Tip(String category, String tipName, String tipDescription) {
        this.category = category;
        this.tipName = tipName;
        this.tipDescription = tipDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTipName() {
        return tipName;
    }

    public void setTipName(String tipName) {
        this.tipName = tipName;
    }

    public String getTipDescription() {
        return tipDescription;
    }

    public void setTipDescription(String tipDescription) {
        this.tipDescription = tipDescription;
    }
}
