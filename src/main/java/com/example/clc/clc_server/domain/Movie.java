package com.example.clc.clc_server.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
public class Movie {

    @Id @GeneratedValue
    private Long id;

    private String url;
    private String title;
    private String title_english;
    private String title_long;
    private String slug;

    private int year;
    private int rating;
    private int runtime;

    private String summary;
    private String description_full;
    private String synopsis;
    private String yt_trailer_code;
    private String language;
    private String mpa_rating;
    private String background_image;
    private String background_image_original;
    private String small_cover_image;
    private String medium_cover_image;
    private String state;
    private LocalDateTime date_uploaded;

    @Embedded
    @Builder.Default
    private List<Genres> genres = new ArrayList<>();
}
