package com.example.loginDemo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.loginDemo.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
