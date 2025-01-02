package com.example.loginDemo.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import com.example.loginDemo.board.entity.Board;
import com.example.loginDemo.board.repository.BoardRepository;
import com.example.loginDemo.board.service.request.BoardCreateRequest;
import com.example.loginDemo.board.service.request.BoardUpdateRequest;
import com.example.loginDemo.board.service.response.BoardCreateResponse;
import com.example.loginDemo.board.service.response.BoardListResponse;
import com.example.loginDemo.board.service.response.BoardReadResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.example.loginDemo.board.repository")
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardCreateResponse create(BoardCreateRequest boardCreateRequest) {
        log.info("board service -> create() called!");
        Board board = boardCreateRequest.toBoard();
        Board createdBoard = boardRepository.save(board);

        return BoardCreateResponse.from(createdBoard);
    }

    @Override
    public List<BoardListResponse> list() {
        log.info("board service -> list() called!");
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDate"));

        return boardList.stream().map(BoardListResponse::from).collect(Collectors.toList());
    }

    @Override
    public BoardReadResponse read(Long id) {
        log.info("board service -> read() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);
        return BoardReadResponse.from(board);
    }

    @Transactional
    @Override
    public BoardReadResponse update(Long id, BoardUpdateRequest boardUpdateRequest) {
        log.info("board service -> update() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);

        if (board != null) {
            board.updatedBoard(
                    boardUpdateRequest.getTitle(),
                    boardUpdateRequest.getContent()
            );
        }

        return BoardReadResponse.from(board);
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        log.info("board service -> delete() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);

        if (board == null) {
            return false;
        }
        else {
            boardRepository.delete(board);
            return true;
        }
    }
}
