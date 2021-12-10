package com.example.clc.clc_server.repository;

import com.example.clc.clc_server.domain.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
