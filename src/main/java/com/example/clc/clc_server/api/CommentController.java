package com.example.clc.clc_server.api;

import java.util.List;

import com.example.clc.clc_server.dto.Result;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestDeleteReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.dto.comment.CommentResponseDto;
import com.example.clc.clc_server.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Result<List<CommentResponseDto>>> getReview(@RequestParam Long movieId){
        List<CommentResponseDto> result = commentService.getCommentsByMovie(movieId);
        Result<List<CommentResponseDto>> body = new Result<>(result);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PostMapping("/api/review")
    ResponseEntity<Result<Long>> saveReview(@RequestBody RequestSaveReviewDto req){
        Long id = commentService.saveComment(req);
        Result<Long> body = new Result<>(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/api/review")
    ResponseEntity<Result<Long>> updateReview(@RequestBody RequestUpdateReviewDto req){
        Long id = commentService.updateComment(req);
        Result<Long> body = new Result<>("resource updated successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/api/review")
    ResponseEntity<Result<Long>> deleteReview(@RequestBody RequestDeleteReviewDto req){
        Long id = commentService.deleteComment(req);
        Result<Long> body = new Result<Long>("resource deleted successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
