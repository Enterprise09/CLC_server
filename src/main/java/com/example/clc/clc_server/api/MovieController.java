package com.example.clc.clc_server.api;

import java.util.List;

import com.example.clc.clc_server.dto.Result;
import com.example.clc.clc_server.dto.movie.MovieCommentDto;
import com.example.clc.clc_server.dto.movie.MovieDto;
import com.example.clc.clc_server.service.MovieService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/api/v2/movies")
    ResponseEntity<Result<List<MovieDto>>> getMovieDtoList(){
        List<MovieDto> result = movieService.findAllToMovieDto(0, 100);
        return ResponseEntity.status(HttpStatus.OK).body(new Result<List<MovieDto>>(result));
    }


    @GetMapping("/api/v2/movie")
    ResponseEntity<Result<MovieCommentDto>> getMovieDto(@RequestParam Long id){
        MovieCommentDto m = movieService.findOneToMovieCommentDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Result<MovieCommentDto>(m));
    }
}
