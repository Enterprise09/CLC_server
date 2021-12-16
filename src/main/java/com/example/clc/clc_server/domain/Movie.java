package com.example.clc.clc_server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.example.clc.clc_server.utility.YtsMovie;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Movie {

    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String url;
    private String title;
    private String titleEnglish;
    private String titleLong;
    private String slug;

    private int year;
    private double rating;
    private int runtime;

    @Lob
    private String summary;

    @Lob
    private String descriptionFull;

    @Lob
    private String synopsis;

    private String ytTrailerCode;
    private String language;
    private String mpaRating;

    private String backgroundImage;
    private String backgroundImageOriginal;
    private String smallCoverImage;
    private String mediumCoverImage;
    private String largeCoverImage;

    /* 연관관계 */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Genre> genres = new ArrayList<>();


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


    /* 생성 메서드 */
    public static Movie fromYtsMovie(YtsMovie ym){
        
        List<String> movieGenres = ym.getGenres();
        List<Genre> genres = new ArrayList<>();

        Movie ret = new Movie.MovieBuilder()
                .url(ym.getUrl()).title(ym.getTitle())
                .titleEnglish(ym.getTitleEnglish())
                .titleLong(ym.getTitleLong())
                .slug(ym.getSlug())
                .year(ym.getYear())
                .rating(ym.getRating())
                .runtime(ym.getRuntime())
                .summary(ym.getSummary())
                .descriptionFull(ym.getDescriptionFull())
                .synopsis(ym.getSynopsis())
                .ytTrailerCode(ym.getYtTrailerCode())
                .language(ym.getLanguage())
                .mpaRating(ym.getMpaRating())
                .backgroundImage(ym.getBackgroundImage())
                .backgroundImageOriginal(ym.getBackgroundImageOriginal())
                .smallCoverImage(ym.getSmallCoverImage())
                .mediumCoverImage(ym.getMediumCoverImage())
                .largeCoverImage(ym.getLargeCoverImage())
                .build();
        
        for (String s : movieGenres) {
            try {
                GenreType gt = GenreType.valueOf(s);
                genres.add(new Genre(gt, ret));
            } catch (IllegalArgumentException ia) {
                ia.printStackTrace();
            }
        }

        genres.forEach(g -> ret.getGenres().add(g));
        
        return ret;
    }
}
