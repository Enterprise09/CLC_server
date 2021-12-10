package com.example.clc.clc_server.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.transaction.Transactional;

import com.example.clc.clc_server.domain.Genres;
import com.example.clc.clc_server.domain.Movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    
    @Test
    void mytest(){
        Movie movie = Movie.builder().description_full("this is desc").rating(100).build();
        assertNotEquals(movie, null);
        assertNotEquals(movie.getGenres(), null);
        
        movie.getGenres().add(Genres.ACTION);
        movie.getGenres().add(Genres.COMEDY);

        movieRepository.save(movie);
        Long movieId = movie.getId();
        log.info("movie id : " + movieId);
        
        Movie result = movieRepository.findById(movieId).get();
        assertEquals(movie, result);

        for(Genres s : result.getGenres()){
            log.info(s.toString());
        }

    }
}
