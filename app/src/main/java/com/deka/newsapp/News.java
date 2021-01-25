package com.deka.newsapp;

public class News {
    private final String title;
    private final String imageUrl;
    private final String description;
    private final String author;
    private final String date;
    private final String time;
    private final String url;

    public News(String title,String imageUrl,String description,String author,String date,String time,String url){
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.description = description;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
