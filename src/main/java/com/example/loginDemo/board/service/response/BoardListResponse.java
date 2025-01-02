package com.example.loginDemo.board.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.entity.Board;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public class BoardListResponse {
    private final Long id;
    private final String title;
    private final String writer;
    private final ZonedDateTime updateDate;

    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getId(),
                board.getTitle(),
                board.getWriter(),
                board.getUpdateDate());
    }

}
