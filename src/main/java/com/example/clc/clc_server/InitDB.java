package com.example.clc.clc_server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.clc.clc_server.domain.Genre;
import com.example.clc.clc_server.domain.GenreType;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.service.MovieService;

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

        movies.add(createMovie("https://yts.mx/movies/mr-saturday-night-2021", "Mr. Saturday Night", 2021,
                "The untold story of Robert Stigwood and how he amped the disco era.",
                "https://yts.mx/assets/images/movies/mr_saturday_night_2021/background.jpg",
                "https://yts.mx/assets/images/movies/mr_saturday_night_2021/medium-cover.jpg",
                new GenreType[] { GenreType.DOCUMENTARY }));

        movies.add(createMovie("https://yts.mx/movies/famous-2021", "Famous", 2021,
                "Famous was filmed live on stage in the height of the #MeToo movement. Inspired by the lives of Young Hollywood stars like River Phoenix, Corey Feldman, Heath Ledger and Corey Haim, Famous takes you on a non-stop thrill ride behind the glamorous world of Young Hollywood and into the mind of A-list celebrity, Jason Mast. What appears to be a night of celebration for his first Oscar nomination, turns out to be an intricate plan to confront his past. Driven by a devastating need to expose the truth, Jason attempts to control the outcome of the evening and ignites a chain of events that pushes the boundaries of real friendship and reveals the true cost of fame. —Michael Leoni",
                "https://yts.mx/assets/images/movies/famous_2021/background.jpg",
                "https://yts.mx/assets/images/movies/famous_2021/medium-cover.jpg",
                new GenreType[] { GenreType.DRAMA }));

        movies.add(createMovie("https://yts.mx/movies/the-last-son-2021", "The Last Son", 2021,
                "An outlaw attempts to end his evil family line.",
                "https://yts.mx/assets/images/movies/the_last_son_2021/background.jpg",
                "https://yts.mx/assets/images/movies/the_last_son_2021/medium-cover.jpg",
                new GenreType[] { GenreType.ACTION, GenreType.DRAMA, GenreType.WESTERN }));

        movies.add(createMovie("https://yts.mx/movies/the-hating-game-2021", "The Hating Game", 2021,
                "As assistants to the co-CEOs of a publishing company, Lucy Hutton and Joshua Templeman sit across from each other every day - and they hate each other. Not dislike. Not begrudgingly tolerate. H-A-T-E. Lucy can't understand Josh's joyless, uptight approach to his job and refusal to smile, which is in stark contrast to her bright clothes, quirkiness, and effusive people-pleasing. Trapped in a shared office 5 days a week, they've become entrenched in an addictive, ridiculous never-ending game of one-upmanship. Lucy can't let Josh beat her at anything - especially when a huge new promotion to be Managing Director is up for grabs. They strike a deal that whoever does not get the promotion must quit. It's game-on - But as tensions reach boiling point and an innocent elevator ride turns into a steamy kiss, Lucy begins to realize just how fine a line there is between love and hate.",
                "https://yts.mx/assets/images/movies/the_hating_game_2021/background.jpg",
                "https://yts.mx/assets/images/movies/the_hating_game_2021/medium-cover.jpg",
                new GenreType[] { GenreType.COMEDY, GenreType.ROMANCE }));

        return movies;
    }

    private Movie createMovie(String url, String title, int year, String summary, String background_image,
            String medium_cover_image, GenreType[] genre) {

        Movie movie = Movie.builder().url(url).title(title).year(year).summary(summary)
                .background_image(background_image).medium_cover_image(medium_cover_image).build();

        for (GenreType gt : genre) {
            movie.getGenres().add(new Genre(gt));
        }

        return movie;
    }

}
