package com.example.clc.clc_server.dto.comment;

import java.time.LocalDateTime;

import com.example.clc.clc_server.domain.Comment;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long docId;
    private String userId;
    private String password;
    private String title;
    private String content;
    private LocalDateTime updatedDate;

    public static CommentResponseDto fromComment(Comment c){
        CommentResponseDto ret = new CommentResponseDto();
        
        ret.setDocId(c.getId());
        ret.setUserId(c.getUserId());
        ret.setPassword(c.getUserPassword());
        ret.setTitle(c.getTitle());
        ret.setContent(c.getContent());
        ret.setUpdatedDate(c.getModifiedDate());

        return ret;
    }
}
