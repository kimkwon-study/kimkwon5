package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.certification.model.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

}
