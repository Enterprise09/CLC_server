package com.example.clc.clc_server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.dto.comment.CommentResponseDto;
import com.example.clc.clc_server.repository.MovieRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class CommentServiceTest {
    
    @Autowired
    CommentService commentService;

    @Autowired
    MovieRepository movieRepository;

    private Long saveComment(String content){
        RequestSaveReviewDto req = new RequestSaveReviewDto();
        req.setMovieId(1L);
        req.setId("test-id");
        req.setPw("test-password");
        req.setContent(content);

        return commentService.saveComment(req);
    }

    @Test
    void save(){
        String content = "this is comment1";

        Long savedId = saveComment(content);
        log.info("saved id : " + savedId);

        CommentResponseDto savedComment = commentService.getComment(savedId);
        log.info("saved id of savedComment : "+ savedComment.getUserId());

        assertEquals("test-id", savedComment.getUserId());
        assertEquals("test-password", savedComment.getPassword());
        assertEquals(content, savedComment.getContent());
    }

    @Test
    void update(){
        String content = "this is comment2";

        // save comment
        Long savedId = saveComment(content);
        CommentResponseDto savedComment = commentService.getComment(savedId);
        log.info("saved id : "+ savedId);
        log.info("[Before]================ comment content in update: "+ savedComment.getContent());

        // create update request dto
        String updatedContent = "this is updated content";

        RequestUpdateReviewDto updateReq = new RequestUpdateReviewDto();
        updateReq.setDocId(savedId);
        updateReq.setMovieId(1L);
        updateReq.setContent(updatedContent);


        // update
        Long updatedId = commentService.updateComment(updateReq);
        savedComment = commentService.getComment(updatedId);

        log.info("[After]================ comment content in update: "+ savedComment.getContent());
        assertEquals("test-id", savedComment.getUserId());
        assertEquals("test-password", savedComment.getPassword());
        assertEquals(updatedContent, savedComment.getContent());
    }


}