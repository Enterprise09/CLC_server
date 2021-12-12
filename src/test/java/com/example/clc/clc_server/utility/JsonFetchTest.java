package com.example.clc.clc_server.utility;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.service.MovieService;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class JsonFetchTest {

    @Autowired
    MovieService movieService;

    // @Test
    void getGenreLists() throws IOException, JSONException {
        List<YtsMovie> parsedMovie = JsonFetch.parseJsonFromYts();

        List<String> genres = parsedMovie.stream().flatMap(m -> m.getGenres().stream().distinct()).distinct()
                .collect(Collectors.toList());

        StringBuffer genresString = new StringBuffer();

        for (String g : genres) {
            genresString.append(g);
            genresString.append(", ");
        }

        log.info(genresString.toString());
    }

    @Test
    void dbinit() throws IOException, JSONException {
        List<YtsMovie> parsedMovie = JsonFetch.parseJsonFromYts();

        List<Movie> movies = parsedMovie.stream().map(Movie::fromYtsMovie).collect(Collectors.toList());
        List<Long> ids = movieService.saveMovies(movies);

        StringBuffer result = new StringBuffer("(");
        for(Long id : ids){
            result.append(id + ", ");
        }
        log.info(result.append(")").toString());
    }

}
