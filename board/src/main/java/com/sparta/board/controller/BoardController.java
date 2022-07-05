package com.sparta.board.controller;

import com.sparta.board.domain.Board;
import com.sparta.board.domain.BoardDeleteRequestDto;
import com.sparta.board.domain.BoardRepository;
import com.sparta.board.domain.BoardRequestDto;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController

public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    //글생성
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

//    @GetMapping("/api/boards")
//    public List<BoardListDto> getMemos() {
//        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
//        ArrayList<BoardListDto> list = new ArrayList<>();
//        for (Board board: boardList) {
//            BoardListDto dto = new BoardListDto(board);
//            list.add(dto);
//        }
//        return list; }

    //게시판조회
    @GetMapping("/api/boards")
    public List<Board> getContents() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }
    //게시판글수정
    @PutMapping("/api/boards/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {

//        Board board = boardRepository.findById(id).orElseThrow(
//                ()->new IllegalArgumentException("아이디가존재하지않습니다")
//        );
//        if(board.getPassword().equals(requestDto.getPassword())){
//            boardService.update(id, requestDto);
//        }
//        return id;
        Optional<Board> board = boardRepository.findById(id);
        if(board.get().getPassword().equals(requestDto.getPassword())) {
            boardService.update(id, requestDto);
            return "업데이트 완료";
        }
        else {
            return "비밀번호 불일치";
        }
    }
    //게시판글삭제
    @DeleteMapping("/api/boards/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardDeleteRequestDto deleteRequestDto) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.get().getPassword().equals(deleteRequestDto.getPassword())) {
            boardRepository.deleteById(id);
            return "삭제완료";
        }
        else {
            return "비밀번호 불일치";
        }
    }

    // 특정 게시물 조회
    @GetMapping("/api/boards/{id}")
    public Board getBoards(@PathVariable Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return board;
    }
}

