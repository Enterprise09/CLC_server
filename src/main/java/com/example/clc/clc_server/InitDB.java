package com.example.clc.clc_server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.service.MovieService;
import com.example.clc.clc_server.utility.JsonFetch;
import com.example.clc.clc_server.utility.YtsMovie;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDB {
    
    private final MovieService movieService;

    @PostConstruct
    void init(){
        movieService.saveMovies(createMovies());
    }

    private List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();
        
        try{
            List<YtsMovie> ytsMovies = JsonFetch.parseJsonFromYts();
            movies = ytsMovies.stream().map(Movie::fromYtsMovie).collect(Collectors.toList());

        }catch(IOException | JSONException e){
            e.printStackTrace();
        }

        return movies;

    }

}
