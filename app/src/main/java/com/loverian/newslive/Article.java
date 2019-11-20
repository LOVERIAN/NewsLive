package com.loverian.newslive;

public class Article {
    private String source;
    private String title;
    private String dec;
    private String url;
    private String img;
    private String content;
    private String time;

    public Article(String source, String title, String dec, String url, String img, String content, String time) {
        this.source = source;
        this.title = title;
        this.dec = dec;
        this.url = url;
        this.img = img;
        this.content = content;
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDec() {
        return dec;
    }

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}