package com.example.clc.clc_server.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Genre {
    
    @Enumerated(EnumType.STRING)
    private GenreType genre;

    public Genre(GenreType genreType){
        this.genre = genreType;
    }
}
