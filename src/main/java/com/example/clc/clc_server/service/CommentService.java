package com.example.clc.clc_server.service;

import com.example.clc.clc_server.domain.Comment;
import com.example.clc.clc_server.repository.CommentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment getComment(Long id){
        return commentRepository.findById(id);
    }

    @Transactional
    public Long saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    @Transactional
    public Long deleteComment(Long id){
        Comment target = commentRepository.findById(id);
        return commentRepository.delete(target);
    }
}
