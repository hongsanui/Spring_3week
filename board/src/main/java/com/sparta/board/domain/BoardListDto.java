package com.sparta.board.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListDto {
    private final Long id;
    private final String title;
    private final String username;
    private final LocalDateTime modifiedAt;

    public BoardListDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.modifiedAt = board.getModifiedAt();
    }
}

