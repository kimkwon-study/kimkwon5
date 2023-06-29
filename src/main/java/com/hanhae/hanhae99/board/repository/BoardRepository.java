package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

}
