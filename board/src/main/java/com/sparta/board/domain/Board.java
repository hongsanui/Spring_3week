package com.sparta.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@NoArgsConstructor //기본생성자
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줌

public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable =false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String contents;


    public Board(String title, String username, String password, String contents) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.contents = contents;
    }

    public void delete(BoardDeleteRequestDto deleteRequestDto){
        this.password = deleteRequestDto.getPassword();
    }
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();

    }
    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

}
