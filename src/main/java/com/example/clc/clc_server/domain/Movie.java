package com.example.clc.clc_server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String url;
    private String title;

    private int year;

    @Lob
    private String summary;

    private String background_image;
    private String medium_cover_image;

    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name="movie_id"))
    @Builder.Default
    private List<Genre> genres = new ArrayList<>();
}
