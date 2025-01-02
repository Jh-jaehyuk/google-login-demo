package com.example.loginDemo.board.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.loginDemo.board.entity.Board;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class BoardCreateRequest {
    private final String title;
    private final String writer;
    private final String content;

    public Board toBoard() {
        return new Board(
                this.title,
                this.writer,
                this.content);
    }
}
