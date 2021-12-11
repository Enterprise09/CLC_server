package com.example.clc.clc_server.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Long> saveMovies(List<Movie> movies){
        List<Long> ids = new ArrayList<>();

        for(Movie m : movies){
            Long id = movieRepository.save(m);
            ids.add(id);
        }

        return ids;
    }

    public Movie findOne(Long id){
        return movieRepository.findById(id);
    } 

    public List<Movie> findAll(int offset, int limit){
        return movieRepository.findAll(offset, limit);
    }
    
}
