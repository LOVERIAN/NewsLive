package com.loverian.newslive;

/**
 * Created by Anil on 9/17/2019.
 */

public class Single_view {
    private String name;
    private String url;
    private String info;

    public Single_view() {
    }

    public Single_view(String name, String url,String info) {
        this.name = name;
        this.url = url;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
