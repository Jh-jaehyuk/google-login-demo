package com.example.loginDemo.board.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardUpdateRequest {
    private final String title;
    private final String content;

}
