package com.example.loginDemo.board.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.entity.Board;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class BoardReadResponse {
    private final String title;
    private final String writer;
    private final String content;

    public static BoardReadResponse from(Board board) {
        if (board == null) {
            return new BoardReadResponse("", "", "");
        }

        return new BoardReadResponse(
                board.getTitle(),
                board.getWriter(),
                board.getContent());
    }
}
