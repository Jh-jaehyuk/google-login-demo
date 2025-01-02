package com.example.loginDemo.board.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.entity.Board;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class BoardCreateResponse {
    private final String title;
    private final String writer;
    private final String content;
    private final ZonedDateTime createDate;
    private final ZonedDateTime updateDate;

    public static BoardCreateResponse from(Board board) {
        if (board == null) {
            return new BoardCreateResponse("", "", "", null, null);
        }

        return new BoardCreateResponse(
                board.getTitle(),
                board.getWriter(),
                board.getContent(),
                board.getCreateDate(),
                board.getUpdateDate()
        );
    }
}
