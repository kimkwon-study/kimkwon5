package com.hanhae.hanhae99.board.service;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.BoardHeart;
import com.hanhae.hanhae99.board.repository.BoardHeartRepository;
import com.hanhae.hanhae99.board.repository.BoardRepository;
import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardHeartService {

    private final BoardRepository boardRepository;
    private final BoardHeartRepository boardHeartRepository;


    public String clickHeart(Long boardId, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_PID)
        );

        Optional<BoardHeart> boardHeart = boardHeartRepository.findByUsernameAndBoard(userDetails.getUsername(),board);

        if (boardHeart.isEmpty()) {
            // 이미 좋아요 했다면 좋아요 취소
            boardHeartRepository.delete(boardHeart.get());
            return "좋아요가 취소 되었습니다. ";
        } else {
            // 좋아요
            boardHeartRepository.save(new BoardHeart(userDetails.getUsername(), board));
            return "좋아요를 누르셨습니다. ";
        }
    }

}
