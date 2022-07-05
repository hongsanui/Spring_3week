package com.sparta.board.service;

import com.sparta.board.domain.Board;
import com.sparta.board.domain.BoardRepository;
import com.sparta.board.domain.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }
}

