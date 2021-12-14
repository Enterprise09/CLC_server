package com.example.clc.clc_server.api;

import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestDeleteReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.service.CommentService;
import com.example.clc.clc_server.service.MovieService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/review")
    void getReview(@RequestParam Long movieId){
        
    }

    @PostMapping("/api/review")
    void saveReview(@RequestBody RequestSaveReviewDto req){
        commentService.saveComment(req);
    }

    @PutMapping("/api/review")
    void updateReview(@RequestBody RequestUpdateReviewDto req){
        commentService.updateComment(req);
    }

    @DeleteMapping("/api/review")
    void deleteReview(@RequestBody RequestDeleteReviewDto req){
        commentService.deleteComment(req);
    }
}
