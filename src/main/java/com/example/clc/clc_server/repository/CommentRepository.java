package com.example.clc.clc_server.repository;

import javax.persistence.EntityManager;

import com.example.clc.clc_server.domain.Comment;

import org.springframework.stereotype.Repository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    
    private final EntityManager em;

    public Long save(Comment comment){
        em.persist(comment);
        return comment.getId();
    }

    public List<Comment> findByMovieId(Long movieId){
        return em.createQuery(
            "select c from Comment c where c.movie.id = :movieId", Comment.class
        )
        .setParameter("movieId", movieId)
        .getResultList();
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
