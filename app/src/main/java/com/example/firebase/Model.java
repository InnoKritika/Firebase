package com.example.firebase;

public class Model {
    private String imageUrl;
    private String category;

    public Model() {
    }
    public Model(String imageUrl, String category){
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

    }
}
