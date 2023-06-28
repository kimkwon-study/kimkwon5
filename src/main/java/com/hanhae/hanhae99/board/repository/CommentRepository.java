package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}