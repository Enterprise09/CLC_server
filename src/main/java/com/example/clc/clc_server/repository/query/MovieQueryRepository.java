package com.example.clc.clc_server.repository.query;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.clc.clc_server.domain.Movie;

import lombok.RequiredArgsConstructor;

// @Repository
@RequiredArgsConstructor
public class MovieQueryRepository {

    private final EntityManager em;

    public List<Movie> findMovies(int offset, int limit){
        return em.createQuery(
            "select m from Movie m"
            , Movie.class)
        .setFirstResult(offset)
        .setMaxResults(limit)
        .getResultList();
    }
    
    // public List<MovieDto> findAllWithGenres(int offset, int limit){
    //     List<Movie> movies = findMovies(offset, limit);
    //     List<Long> ids = getMovieIds(movies);

    //     return em.createQuery(
    //         "select m from Movie m"+
    //         " join m.movie m"+
    //         " where g.movie.id in :movieIds", Genre.class
    //     )
    //     .setParameter("movieIds", ids)
    //     .getResultList();
    // }
}
