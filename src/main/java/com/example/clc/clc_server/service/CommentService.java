package com.example.clc.clc_server.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestDeleteReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.dto.comment.CommentResponseDto;
import com.example.clc.clc_server.repository.CommentRepository;
import com.example.clc.clc_server.repository.MovieRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;

    public CommentResponseDto getComment(Long id) {
        Comment result = commentRepository.findById(id);
        return CommentResponseDto.fromComment(result);
    }

    public List<CommentResponseDto> getCommentsByMovie(Long movieId){
        List<Comment> result = commentRepository.findByMovieId(movieId);
        return result.stream().map(CommentResponseDto::fromComment).collect(Collectors.toList());
    }

    @Transactional
    public Long saveComment(RequestSaveReviewDto dto){
        Movie movie = movieRepository.findById(dto.getMovieId());
        Comment comment = Comment.createComment(dto.getId(), dto.getPw(), dto.getContent(), movie);

        return commentRepository.save(comment);
    }

    @Transactional
    public Long updateComment(RequestUpdateReviewDto dto) {
        Comment comment = commentRepository.findById(dto.getDocId());
        comment.updateContent(dto.getContent());

        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long deleteComment(RequestDeleteReviewDto dto) {
        Comment target = commentRepository.findById(dto.getDocId());
        return commentRepository.delete(target);
    }
}
