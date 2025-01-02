package com.example.loginDemo.board.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.loginDemo.board.service.response.BoardReadResponse;

@RequiredArgsConstructor
@Getter
public class BoardReadResponseForm {
    private final String title;
    private final String writer;
    private final String content;

    public static BoardReadResponseForm from(BoardReadResponse boardReadResponse) {
        return new BoardReadResponseForm(
                boardReadResponse.getTitle(),
                boardReadResponse.getWriter(),
                boardReadResponse.getContent());
    }
}
