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

@Component // 운용환경에서는 처음 DB값이 잘 들어갔으면 주석처리해서 불필요한 데이터 삽입을 막기. 테스트 환경에서는 ddl-auto가 보통 create인데 db에 값을 매번 넣어주기 위해 그냥 둔다.
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
