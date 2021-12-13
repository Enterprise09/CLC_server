package com.example.clc.clc_server.repository;

import javax.persistence.EntityManager;

import com.example.clc.clc_server.domain.Comment;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    
    private final EntityManager em;

    public Long save(Comment comment){
        em.persist(comment);
        return comment.getId();
    }

    public Comment findById(Long id){
        return em.find(Comment.class, id);
    }

    public Long delete(Comment comment){
        Long ret = comment.getId();
        em.remove(comment);

        return ret;
    }

}
