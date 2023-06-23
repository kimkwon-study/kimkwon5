package com.hanhae.hanhae99.repository;

import com.hanhae.hanhae99.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}