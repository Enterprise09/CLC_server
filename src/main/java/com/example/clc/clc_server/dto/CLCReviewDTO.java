package com.example.clc.clc_server.dto;

public class CLCReviewDTO {
    private String title;
    private String content;
    private String id;
    private String pw;
    private String movieId;

    public CLCReviewDTO(String title, String content, String id, String pw, String movieId) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.pw = pw;
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    
}
