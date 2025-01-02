package com.example.loginDemo.board.controller;

import com.example.loginDemo.board.controller.request_form.BoardCreateRequestForm;
import com.example.loginDemo.board.controller.request_form.BoardUpdateRequestForm;
import com.example.loginDemo.board.controller.response_form.BoardCreateResponseForm;
import com.example.loginDemo.board.controller.response_form.BoardListResponseForm;
import com.example.loginDemo.board.controller.response_form.BoardReadResponseForm;
import com.example.loginDemo.board.service.BoardService;
import com.example.loginDemo.board.service.response.BoardCreateResponse;
import com.example.loginDemo.board.service.response.BoardListResponse;
import com.example.loginDemo.board.service.response.BoardReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public BoardCreateResponseForm create(@RequestBody BoardCreateRequestForm boardCreateRequestForm) {
        log.info("board controller -> create() called!");

        BoardCreateResponse response = boardService.create(boardCreateRequestForm.toBoardCreateRequest());
        return BoardCreateResponseForm.from(response);
    }

    @GetMapping("/list")
    public List<BoardListResponseForm> list() {
        log.info("board controller -> list() called!");

        List<BoardListResponse> responseList = boardService.list();
        return responseList.stream().map(BoardListResponseForm::from).collect(Collectors.toList());
    }

    @GetMapping("/read/{id}")
    public BoardReadResponseForm read(@PathVariable Long id) {
        log.info("board controller -> read() called!");

        BoardReadResponse response = boardService.read(id);
        return BoardReadResponseForm.from(response);
    }

    @PutMapping("/modify/{id}")
    public BoardReadResponseForm update(@PathVariable Long id, @RequestBody BoardUpdateRequestForm boardUpdateRequestForm) {
        log.info("board controller -> update() called!");

        BoardReadResponse response = boardService.update(id, boardUpdateRequestForm.toBoardUpdateRequest());
        return BoardReadResponseForm.from(response);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.info("board controller -> delete() called!");

        Boolean response = boardService.delete(id);
        return response;
    }
}
