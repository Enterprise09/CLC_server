package com.example.clc.clc_server.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.domain.Movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class CommentRepositoryTest {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired 
    private MovieRepository movieRepository;

    @Test
    void saveTest(){
        Movie movie = Movie.builder().title("this is movie").runtime(200).build();
        Comment comment = Comment.createComment("test-id", "test-password", "Hello World!!", movie);

        movie.getComments().add(comment);
        Long movieId = movieRepository.save(movie);

        Comment result = commentRepository.findById(comment.getId());

        assertEquals("test-id", result.getUserId());
        assertEquals("Hello World!!", result.getContent());
        assertEquals(movie.getTitle(), result.getMovie().getTitle());

        movie = movieRepository.findById(movieId);
        log.info(movie.getComments().get(0).getUserId());
        assertEquals("test-id", movie.getComments().get(0).getUserId());
    }
}
