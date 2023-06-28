package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentRepository> findAllByOrderByCreatedAtDesc();
}