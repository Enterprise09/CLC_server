package com.example.clc.clc_server.dto.comment;

import com.example.clc.clc_server.domain.Comment;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long docId;
    private String id;
    private String password;
    private String content;

    public static CommentResponseDto fromComment(Comment c){
        CommentResponseDto ret = new CommentResponseDto();
        
        ret.setDocId(c.getId());
        ret.setId(c.getUserId());
        ret.setPassword(c.getUserPassword());
        ret.setContent(c.getContent());

        return ret;
    }
}
