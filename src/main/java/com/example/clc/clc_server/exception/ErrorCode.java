package com.example.clc.clc_server.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 401 */
    BAD_COMMENT_REQUEST(HttpStatus.BAD_REQUEST, "글을 조회/등록/수정/삭제 하는데 필수 필드가 빠져있습니다."),
    BAD_MOVIE_REQUEST(HttpStatus.BAD_REQUEST, "영화를 조회/등록/수정/삭제 하는데 필수 필드가 빠져있습니다."),

    /* 404 */
    MOVIE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 영화입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 글입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
