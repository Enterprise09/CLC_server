package com.example.clc.clc_server.dto.comment;

import lombok.Data;

public class CommentRequestDto {

    @Data
    public static class RequestSaveReviewDto{
        private Long movieId;
        private String id;
        private String pw;
        private String title;
        private String content;
    }

    @Data
    public static class RequestUpdateReviewDto{
        private String title;
        private String content;

        private Long docId;
        private Long movieId;

        // private String id;
        // private String pw;
    }

    @Data
    public static class RequestDeleteReviewDto{
        private Long docId;
        // private String id;
        // private String pw;
    }
}
