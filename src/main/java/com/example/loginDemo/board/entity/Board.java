package com.example.loginDemo.board.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;

@Entity
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private String content;
    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;

    @PrePersist
    public void prePersist() {
        this.createDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = ZonedDateTime.now();
    }

    public void updatedBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board() {}
}
