package com.example.clc.clc_server.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String userPassword;

    @Lob
    private String content;

    /* 연관관계 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    @JsonIgnore
    private Movie movie;


    /* 생성 메서드 */
    // private Comment(String userId, String userPassword, String content){
    //     this.userId = userId;
    //     this.userPassword = userPassword;
    //     this.content = content;
    // }

    private Comment(String userId, String userPassword, String content, Movie movie){
        this.userId = userId;
        this.userPassword = userPassword;
        this.content = content;
        this.movie = movie;
    }

    public static Comment createComment(String userId, String userPassword, String content, Movie movie){
        Comment ret = new Comment(userId, userPassword, content, movie);
        return ret;
    }

    public static Comment createComment(Long id, String userId, String userPassword, String content, Movie movie){
        Comment ret = new Comment(id, userId, userPassword, content, movie);
        return ret;
    }

    public void updateContent(String content){
        this.content = content;
    }
}
