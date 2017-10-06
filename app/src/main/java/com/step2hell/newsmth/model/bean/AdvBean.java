package com.step2hell.newsmth.model.bean;


public class AdvBean {
    private final static String PREFIX = "http://images.newsmth.net/nForum";

    private String url;
    private String file;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile() {
        return PREFIX + file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "AdvBean{" +
                "url='" + url + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
