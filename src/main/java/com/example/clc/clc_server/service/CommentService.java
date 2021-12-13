package com.example.clc.clc_server.service;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestDeleteReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.repository.CommentRepository;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;

    public Comment getComment(Long id){
        return commentRepository.findById(id);
    }

    @Transactional
    public Long saveComment(RequestSaveReviewDto dto){
        Movie movie = movieRepository.findById(dto.getMovieId());
        Comment comment = Comment.createComment(dto.getId(), dto.getPw(), dto.getContent(), movie);

        return commentRepository.save(comment);
    }

    @Transactional
    public Long updateComment(RequestUpdateReviewDto dto){
        Comment comment = commentRepository.findById(dto.getDocId());
        String content = dto.getContent();

        Comment updatedComment = Comment.createComment(comment.getId(), comment.getUserId(), comment.getUserPassword(),content,comment.getMovie());
        commentRepository.save(updatedComment);

        return updatedComment.getId();
    }

    @Transactional
    public Long deleteComment(RequestDeleteReviewDto dto){
        Comment target = commentRepository.findById(dto.getDocId());
        return commentRepository.delete(target);
    }
}
