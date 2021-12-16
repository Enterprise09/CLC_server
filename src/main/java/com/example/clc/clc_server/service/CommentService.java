package com.example.clc.clc_server.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.domain.Movie;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestDeleteReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestSaveReviewDto;
import com.example.clc.clc_server.dto.comment.CommentRequestDto.RequestUpdateReviewDto;
import com.example.clc.clc_server.exception.CustomException;
import com.example.clc.clc_server.exception.ErrorCode;
import com.example.clc.clc_server.dto.comment.CommentResponseDto;
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

    public CommentResponseDto getComment(Long id) {
        Comment result = commentRepository.findById(id);
        if(result == null){
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }
        return CommentResponseDto.fromComment(result);
    }


    public List<CommentResponseDto> getCommentsByMovie(Long movieId){
        List<Comment> result = commentRepository.findByMovieId(movieId);

        if(result == null){
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        return result.stream().map(CommentResponseDto::fromComment).collect(Collectors.toList());
    }


    @Transactional
    public Long saveComment(RequestSaveReviewDto dto){
        if(dto.getMovieId() == null || dto.getId() == null || dto.getPw() == null || dto.getTitle() == null || dto.getContent() == null){
            throw new CustomException(ErrorCode.BAD_COMMENT_REQUEST);
        }

        Movie movie = movieRepository.findById(dto.getMovieId());
        if(movie == null){
            throw new CustomException(ErrorCode.MOVIE_NOT_FOUND);
        }

        Comment comment = Comment.createComment(dto.getId(), dto.getPw(), dto.getTitle(), dto.getContent(), movie);
        return commentRepository.save(comment);
    }


    @Transactional
    public Long updateComment(RequestUpdateReviewDto dto) {
        if(dto.getDocId() == null || dto.getContent() == null || dto.getTitle() == null || dto.getContent() == null){
            throw new CustomException(ErrorCode.BAD_COMMENT_REQUEST);
        }

        Comment comment = commentRepository.findById(dto.getDocId());
        comment.updateTitle(dto.getTitle());
        comment.updateContent(dto.getContent());

        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long deleteComment(RequestDeleteReviewDto dto) {
        if(dto.getDocId() == null){
            throw new CustomException(ErrorCode.BAD_COMMENT_REQUEST);
        }

        Comment target = commentRepository.findById(dto.getDocId());
        return commentRepository.delete(target);
    }
}
