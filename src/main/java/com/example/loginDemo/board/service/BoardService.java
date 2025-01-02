package com.example.loginDemo.board.service;

import com.example.loginDemo.board.service.request.BoardUpdateRequest;
import com.example.loginDemo.board.service.request.BoardCreateRequest;
import com.example.loginDemo.board.service.response.BoardCreateResponse;
import com.example.loginDemo.board.service.response.BoardListResponse;
import com.example.loginDemo.board.service.response.BoardReadResponse;

import java.util.List;

public interface BoardService {
    BoardCreateResponse create(BoardCreateRequest boardCreateRequest);
    List<BoardListResponse> list();
    BoardReadResponse read(Long id);
    BoardReadResponse update(Long id, BoardUpdateRequest boardUpdateRequest);
    Boolean delete(Long id);
}
