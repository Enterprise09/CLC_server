package com.example.clc.clc_server.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Genre {
    
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    @JsonIgnore
    private Movie movie;

    @Enumerated(EnumType.STRING)
    private GenreType genre;

    Genre(GenreType gt, Movie m){
        this.genre = gt;
        this.movie = m;
    }

    public static Genre createGenre(GenreType gt, Movie m){
        return new Genre(gt, m);
    }

}
