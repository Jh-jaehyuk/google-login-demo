package com.example.loginDemo.board.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.service.response.BoardCreateResponse;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class BoardCreateResponseForm {
    private final String title;
    private final String writer;
    private final String content;
    private final ZonedDateTime createDate;
    private final ZonedDateTime updateDate;

    public static BoardCreateResponseForm from(BoardCreateResponse boardCreateResponse) {
        return new BoardCreateResponseForm(
                boardCreateResponse.getTitle(),
                boardCreateResponse.getWriter(),
                boardCreateResponse.getContent(),
                boardCreateResponse.getCreateDate(),
                boardCreateResponse.getUpdateDate());
    }
}
