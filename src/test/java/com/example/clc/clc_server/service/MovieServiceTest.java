package com.example.clc.clc_server.service;

import com.example.clc.clc_server.domain.Genre;
import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.Movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class MovieServiceTest {
    
    @Autowired
    private MovieService movieService;

    @Test
    void test(){
        Movie m = createMovie("https://yts.mx/movies/mr-saturday-night-2021", "Mr. Saturday Night", 2021,
        "The untold story of Robert Stigwood and how he amped the disco era.",
        "https://yts.mx/assets/images/movies/mr_saturday_night_2021/background.jpg",
        "https://yts.mx/assets/images/movies/mr_saturday_night_2021/medium-cover.jpg",
        new GenreType[] { GenreType.Documentary });

        Long result = movieService.save(m);
        Movie result1 = movieService.findOne(result);
        log.info(result1.getTitle());
    }

    public static Movie createMovie(String url, String title, int year, String summary, String background_image,
            String medium_cover_image, GenreType[] genreTypes) {

        Movie movie = Movie.builder().url(url).title(title).year(year).summary(summary)
                .backgroundImage(background_image).mediumCoverImage(medium_cover_image).build();

        for (GenreType gt : genreTypes) {
            movie.getGenres().add(Genre.createGenre(gt, movie));
        }

        return movie;
    }    
}
