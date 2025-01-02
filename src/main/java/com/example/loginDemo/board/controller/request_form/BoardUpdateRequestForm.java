package com.example.loginDemo.board.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.service.request.BoardUpdateRequest;

@Getter
@RequiredArgsConstructor
public class BoardUpdateRequestForm {
    private final String title;
    private final String content;

    public BoardUpdateRequest toBoardUpdateRequest() {
        return new BoardUpdateRequest(
                this.title,
                this.content
        );
    }
}
