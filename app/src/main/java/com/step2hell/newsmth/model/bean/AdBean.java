package com.step2hell.newsmth.model.bean;

import com.google.gson.annotations.SerializedName;

public class AdBean {
    private final static String PREFIX = "http://images.newsmth.net/nForum";

    @SerializedName("url")
    private String artile;

    @SerializedName("file")
    private String image;

    public String getArticle() {
        return artile;
    }

    public void setArticle(String artile) {
        this.artile = artile;
    }

    public String getImage() {
        return PREFIX + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AdBean{" +
                "artile='" + artile + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
