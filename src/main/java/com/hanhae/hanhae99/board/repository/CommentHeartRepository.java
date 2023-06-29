package com.hanhae.hanhae99.board.repository;

import com.hanhae.hanhae99.board.model.entity.Comment;
import com.hanhae.hanhae99.board.model.entity.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {

    Optional<CommentHeart> findByUsernameAndComment(String userName, Comment comment);

}