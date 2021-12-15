package com.example.clc.clc_server.service;

import java.util.ArrayList;
import java.util.List;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
        Movie ret = movieRepository.findById(id);
        return ret;
    } 

    public List<Movie> findAll(int offset, int limit){
        return movieRepository.findMovies(offset, limit);
    }
    
}
