package com.hanhae.hanhae99.board.service;

import com.hanhae.hanhae99.certification.model.entity.User;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.request.BoardSaveRequest;
import com.hanhae.hanhae99.board.model.response.BoardResponse;
import com.hanhae.hanhae99.board.repository.BoardRepository;
import com.hanhae.hanhae99.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    @Transactional
    public BoardResponse save(BoardSaveRequest boarReq, User user) {

        Board board = repository.save(Board.builder()
                .title(boarReq.title())
                .name(user.getUsername())
                .content(boarReq.content())
                .build());
        return Board.changeEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findAll() {
        return repository.findAllByOrderByCreatedAtDesc().stream()
                .map(a -> Board.changeEntity(a))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardResponse findById(Long id) {
        Board board = repository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NO_PID)
        );
        return Board.changeEntity(board);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardSaveRequest req, User user) {

        Board board = repository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NO_PID)
        );

        if (checkIf(user, board)) {
            board.setTitle(req.title());
            board.setContent(req.content());
        }

        return Board.changeEntity(board);

    }

    public String deleteBoard(Long id,  User user) {
        Board board = repository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NO_PID)
        );

        if (checkIf(user, board)) {
            repository.deleteById(id);
        }

        return "성공적으로 삭제되었습니다.";
    }

    public boolean checkIf(User user, Board board) {
        if (UserRoleEnum.ADMIN.toString().equals(user.getRole().getRole())) {
            return true;
        } else {
            if (!(board.getName().equals(user.getUsername()))) {
                //TODO 에러
                throw new CustomException(ErrorCode.NO_PASSWORD);
            }
        }
        return true;
    }


}
