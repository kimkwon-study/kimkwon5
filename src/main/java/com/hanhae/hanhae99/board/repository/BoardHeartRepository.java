package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.BoardHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardHeartRepository extends JpaRepository<BoardHeart, Long> {
    BoardHeart findByBoardAndUsername(Board board, String username);
}
