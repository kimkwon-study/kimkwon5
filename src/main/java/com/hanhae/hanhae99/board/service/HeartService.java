package com.hanhae.hanhae99.board.service;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.BoardHeart;
import com.hanhae.hanhae99.board.repository.BoardHeartRepository;
import com.hanhae.hanhae99.board.repository.BoardRepository;
import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.certification.model.entity.User;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final BoardRepository boardRepository;
    private final BoardHeartRepository boardHeartRepository;


    public String clickHeart(Long boardId, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_PID)
        );

        BoardHeart boardHeart = boardHeartRepository.findByBoardAndUsername(board, userDetails.getUsername());

        if (boardHeart != null) {
            // 이미 좋아요 했다면 좋아요 취소
            boardHeartRepository.delete(boardHeart);
            return "좋아요가 취소 되었습니다. ";
        } else {
            // 좋아요
            boardHeart = new BoardHeart(true, userDetails.getUsername(), board);
            boardHeartRepository.save(boardHeart);
            return "좋아요를 누르셨습니다. ";
        }
    }

}
