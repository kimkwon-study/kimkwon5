package com.hanhae.hanhae99.service;

import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.type.ErrorCode;
import com.hanhae.hanhae99.model.entity.Board;
import com.hanhae.hanhae99.model.request.BoardSaveRequest;
import com.hanhae.hanhae99.model.response.BoardResponse;
import com.hanhae.hanhae99.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    @Transactional
    public BoardResponse save(BoardSaveRequest req) {
        Board board = repository.save(Board.builder()
                .title(req.title())
                .name(req.name())
                .content(req.content())
                .password(req.password())
                .build());
        return Board.changeEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findAll() {
        return repository.findAll().stream()
                .map(a -> Board.changeEntity(a))
                .sorted(Comparator.comparing(BoardResponse::createdAt).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardResponse findById(Long id) {
        Board board = repository.findById(id).orElseThrow(()->
                new CustomException(ErrorCode.NO_PID)
        );
        return Board.changeEntity(board);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardSaveRequest req){
        Board board = repository.findById(id).orElseThrow(()->
                new CustomException(ErrorCode.NO_PID)
        );
        if(!(board.getPassword().equals(req.password()))){
            //TODO 에러
            throw new CustomException(ErrorCode.NO_PASSWORD);
        }
        board.setName(req.name());
        board.setTitle(req.title());
        board.setContent(req.content());
        return Board.changeEntity(board);
    }

    public String deleteBoard(Long id, String password){
        Board board = repository.findById(id).orElseThrow(()->
                new CustomException(ErrorCode.NO_PID)
        );
        if(!(board.getPassword().equals(password))){
            //TODO 에러
            throw new CustomException(ErrorCode.NO_PASSWORD);
        }
        repository.deleteById(id);
        return "성공적으로 삭제되었습니다.";
    }


}
