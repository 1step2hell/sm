package com.step2hell.newsmth.model.bean;

/**
 * Created by bob on 10/4/17.
 */

public class AdvBean {
    String url;
    String file;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile() {
        return file;
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
