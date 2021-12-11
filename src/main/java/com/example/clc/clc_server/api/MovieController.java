package com.example.clc.clc_server.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping("/api/v1/movies")
    Result<List<Movie>> getMovieList() {
        List<Movie> movies = movieRepository.findAll(0, 100);
        return new Result<List<Movie>>(movies);
    }

    @GetMapping("/api/v2/movies")
    Result<List<MovieDto>> getMovieDtoList(){
        List<Movie> movies = movieRepository.findAll(0, 100);
        List<MovieDto> result = movies.stream().map(MovieDto::new).collect(Collectors.toList());
        return new Result<List<MovieDto>>(result);
    }

    @Data
    static class MovieDto {
        private Long id;
        private String url;
        private String title;
        private int year;
        private String summary;
        private String background_image;
        private String medium_cover_image;
        private List<GenreType> genres = new ArrayList<>();

        MovieDto(Movie m) {
            this.id = m.getId();
            this.url = m.getUrl();
            this.title = m.getTitle();
            this.year = m.getYear();
            this.summary = m.getSummary();
            this.background_image = m.getBackground_image();
            this.medium_cover_image = m.getMedium_cover_image();
            this.genres = m.getGenres().stream().map(g -> g.getGenre()).collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
