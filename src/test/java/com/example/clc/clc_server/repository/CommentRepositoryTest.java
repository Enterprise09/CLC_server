package com.example.clc.clc_server.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.domain.Movie;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CommentRepositoryTest {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired 
    private MovieRepository movieRepository;

    @Test
    void getTest(){
        Comment c = commentRepository.findById(1L);
        assertEquals(null, c);
    }

    @Test @Disabled
    void saveTest(){
        Movie movie = Movie.builder().title("this is movie").runtime(200).build();
        Comment comment1 = Comment.createComment("test-id", "test-password", "comment1", "Hello World!!", movie);
        Comment comment2 = Comment.createComment("test-id", "test-password", "comment2", "This is comment2", movie);

        movie.getComments().add(comment1);
        movie.getComments().add(comment2);
        Long movieId = movieRepository.save(movie);

        Comment result = commentRepository.findById(comment1.getId());

        assertEquals("test-id", result.getUserId());
        assertEquals("Hello World!!", result.getContent());
        assertEquals(movie.getTitle(), result.getMovie().getTitle());

        movie = movieRepository.findById(movieId);
        assertEquals("test-id", movie.getComments().get(0).getUserId());
    }

    @Test @Disabled
    void getCommentListByMovie(){
        Movie movie = Movie.builder().title("this is movie2").runtime(200).build();
        Comment comment1 = Comment.createComment("test-id", "test-password", "comment1", "Hello World!!", movie);
        Comment comment2 = Comment.createComment("test-id", "test-password", "comment2", "This is comment2", movie);

        movie.getComments().add(comment1);
        movie.getComments().add(comment2);

        Long movieId = movieRepository.save(movie);
        
        List<Comment> commentList = commentRepository.findByMovieId(movieId);
        
        assertEquals("Hello World!!", commentList.get(0).getContent());
        assertEquals("This is comment2", commentList.get(1).getContent());
    }
}
