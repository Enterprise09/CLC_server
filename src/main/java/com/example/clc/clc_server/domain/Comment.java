package com.example.clc.clc_server.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String userPassword;

    @Lob
    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /* 연관관계 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    @JsonIgnore
    private Movie movie;

    /* 생성자 */
    private Comment(String userId, String userPassword, String title, String content, Movie movie){
        this.userId = userId;
        this.userPassword = userPassword;
        this.title = title;
        this.content = content;
        this.movie = movie;
    }
    
    private Comment(Long id, String userId, String userPassword, String title, String content, Movie movie){
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.title = title;
        this.content = content;
        this.movie = movie;
    }


    /* 생성메서드 */
    public static Comment createComment(String userId, String userPassword, String title, String content, Movie movie){
        Comment ret = new Comment(userId, userPassword, title, content, movie);
        return ret;
    }

    public static Comment createComment(Long id, String userId, String userPassword, String title, String content, Movie movie){
        Comment ret = new Comment(id, userId, userPassword, title, content, movie);
        return ret;
    }

    /* 일단은 불가피한 setter */
    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.content = content;
    }
}
