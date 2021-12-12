package com.example.clc.clc_server.api;

import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.movie.MovieDto;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.http.HttpStatus;
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
        List<Movie> movies = movieRepository.findMovies(0, 100);
        return new Result<List<Movie>>(HttpStatus.OK ,movies);
    }

    @GetMapping("/api/v2/movies")
    Result<List<MovieDto>> getMovieDtoList(){
        List<Movie> movies = movieRepository.findMovies(0, 100);
        List<MovieDto> result = movies.stream().map(MovieDto::new).collect(Collectors.toList());
        return new Result<List<MovieDto>>(HttpStatus.OK, result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        HttpStatus status;
        private T data;
    }
}
