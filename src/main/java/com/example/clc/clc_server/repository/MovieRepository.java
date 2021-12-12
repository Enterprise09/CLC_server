package com.example.clc.clc_server.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.clc.clc_server.domain.Movie;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
    
    private final EntityManager em;
    
    public Long save(Movie m){
        em.persist(m);
        return m.getId();
    }

    public Movie findById(Long id){
        return em.find(Movie.class, id);
    }

    public List<Movie> findMovies(int offset, int limit){
        List<Movie> ret = em.createQuery("select m from Movie m", Movie.class)
        .setFirstResult(offset)
        .setMaxResults(limit)
        .getResultList();

        return ret;
    }

}
