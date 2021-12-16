package com.example.clc.clc_server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.movie.MovieDto;
import com.example.clc.clc_server.exception.CustomException;
import com.example.clc.clc_server.exception.ErrorCode;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Long save(Movie m){
        return movieRepository.save(m);
    }

    @Transactional
    public List<Long> saveMovies(List<Movie> movies){
        List<Long> ids = new ArrayList<>();

        for(Movie m : movies){
            Long id = movieRepository.save(m);
            ids.add(id);
        }

        return ids;
    }

    public Movie findOne(Long id){
        if(id == null){
            throw new CustomException(ErrorCode.BAD_MOVIE_REQUEST);
        }

        Movie ret = movieRepository.findById(id);
        if(ret == null){
            throw new CustomException(ErrorCode.MOVIE_NOT_FOUND);
        }

        return ret;
    } 

    public List<Movie> findAll(int offset, int limit){
        return movieRepository.findMovies(offset, limit);
    }

    public List<MovieDto> findAllToMovieDto(int offset, int limit){
        List<Movie> movies = movieRepository.findMovies(0, 100);
        return movies.stream().map(MovieDto::new).collect(Collectors.toList());
    }
    
}
