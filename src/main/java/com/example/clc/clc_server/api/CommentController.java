package com.example.clc.clc_server.api;

import com.example.clc.clc_server.service.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/review")
    void saveReview(@RequestBody RequestSaveReviewDto req){

    }

    @PutMapping("/api/review")
    void updateReview(@RequestBody RequestUpdateReviewDto req){

    }

    @DeleteMapping("/api/review")
    void deleteReview(@RequestBody RequestDeleteReviewDto req){

    }

    @Data
    static class RequestSaveReviewDto{
        private Long docId;
        private Long movieId;
        private String id;
        private String pw;
        private String content;
    }

    @Data
    static class RequestUpdateReviewDto{
        private String content;
        private Long docId;
        private Long movieId;
        private String id;
        private String pw;
    }

    @Data
    static class RequestDeleteReviewDto{
        private Long docId;
        private Long movieId;
        private String id;
        private String pw;
    }
}
