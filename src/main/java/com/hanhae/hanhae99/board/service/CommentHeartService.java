package com.hanhae.hanhae99.board.service;

import com.hanhae.hanhae99.board.model.entity.Comment;
import com.hanhae.hanhae99.board.model.entity.CommentHeart;
import com.hanhae.hanhae99.board.repository.CommentHeartRepository;
import com.hanhae.hanhae99.board.repository.CommentRepository;
import com.hanhae.hanhae99.certification.model.entity.User;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentHeartService {

    private final CommentHeartRepository commentHeartRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public String clickHeart(Long commentPid, User user){
        Comment comment = commentRepository.findById(commentPid)
                .orElseThrow(() ->{
                    throw new CustomException(ErrorCode.WRONG_COMMENT_PID);
                });
        Optional<CommentHeart> heart = commentHeartRepository.findByUsername(user.getUsername());
        if(heart.isEmpty()){
            commentHeartRepository.save(CommentHeart.builder()
                            .username(user.getUsername())
                            .comment(comment)
                    .build()
            );
            return "좋아요 성공!";
        }else{
            commentHeartRepository.delete(heart.get());
            return "좋아요 취소!";
        }
    }

}
