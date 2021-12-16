package com.example.clc.clc_server.dto.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.comment.CommentResponseDto;

import lombok.Data;

@Data
public class MovieCommentDto {
    private Long id;
    private String url;
    private String title;
    private String title_english;
    private String title_long;
    private String slug;

    private int year;
    private double rating;
    private int runtime;

    private String summary;
    private String description_full;
    private String synopsis;

    private String background_image;
    private String medium_cover_image;
    private String large_cover_image;

    private List<GenreType> genres = new ArrayList<>();
    private List<CommentResponseDto> comments = new ArrayList<>();

    public MovieCommentDto(Movie m) {
        this.id = m.getId();
        this.url = m.getUrl();
        this.title = m.getTitle();
        this.title_english = m.getTitleEnglish();
        this.title_long = m.getTitleLong();
        this.slug = m.getSlug();

        this.year = m.getYear();
        this.rating = m.getRating();
        this.runtime = m.getRuntime();

        this.summary = m.getSummary();
        this.description_full = m.getDescriptionFull();
        this.synopsis = m.getSynopsis();

        this.background_image = m.getBackgroundImage();
        this.medium_cover_image = m.getMediumCoverImage();
        this.large_cover_image = m.getLargeCoverImage();

        this.genres = m.getGenres().stream().map(g -> g.getGenre()).collect(Collectors.toList());
        this.comments = m.getComments().stream().map(CommentResponseDto::fromComment).collect(Collectors.toList());
    }
}
