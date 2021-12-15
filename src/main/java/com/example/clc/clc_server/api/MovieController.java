package com.example.clc.clc_server.api;

import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.Result;
import com.example.clc.clc_server.dto.movie.MovieCommentDto;
import com.example.clc.clc_server.dto.movie.MovieDto;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping("/api/v1/movies")
    ResponseEntity<Result<List<Movie>>> getMovieList() {
        List<Movie> movies = movieRepository.findMovies(0, 100);
        return ResponseEntity.status(HttpStatus.OK).body(new Result<List<Movie>>(movies));
    }

    @GetMapping("/api/v2/movies")
    ResponseEntity<Result<List<MovieDto>>> getMovieDtoList(){
        List<Movie> movies = movieRepository.findMovies(0, 100);
        List<MovieDto> result = movies.stream().map(MovieDto::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new Result<List<MovieDto>>(result));
    }

    @GetMapping("/api/v2/movie")
    ResponseEntity<Result<MovieCommentDto>> getMovieDto(@RequestParam Long id){
        Movie m = movieRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Result<MovieCommentDto>(new MovieCommentDto(m)));
    }
}
