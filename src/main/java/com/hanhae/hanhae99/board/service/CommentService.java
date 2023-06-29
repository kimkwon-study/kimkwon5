package com.hanhae.hanhae99.board.service;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.Comment;
import com.hanhae.hanhae99.board.model.request.CommentRequest;
import com.hanhae.hanhae99.board.model.response.CommentResponse;
import com.hanhae.hanhae99.board.repository.BoardRepository;
import com.hanhae.hanhae99.board.repository.CommentRepository;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import com.hanhae.hanhae99.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponse saveComment(CommentRequest commentRequest, Long boardPid, String username) {
        Board board = boardRepository.findById(boardPid).orElseThrow(() ->
        {
            throw new CustomException(ErrorCode.WRONG_BOARD_PID);
        });
        Comment comment = commentRepository.save(Comment.builder()
                .content(commentRequest.content())
                .name(username)
                .board(board)
                .build());
        if(Objects.isNull(comment.getCommentHearts())){
            return new CommentResponse(
                    comment.getName(),
                    comment.getContent(),
                    0
            );
        }else{
            return new CommentResponse(
                    comment.getName(),
                    comment.getContent(),
                    comment.getCommentHearts().size()
            );
        }
    }

    @Transactional
    public CommentResponse updateComment(CommentRequest commentRequest, Long commentPid, UserDetails userDetails) {
        Comment comment = commentRepository.findById(commentPid).orElseThrow(() -> {
            throw new CustomException(ErrorCode.WRONG_COMMENT_PID);
        });
        if (checkAuthority(comment, userDetails)) {
            comment.setContent(commentRequest.content());
        }
        return new CommentResponse(comment.getName(), commentRequest.content(),
                comment.getCommentHearts().size());
    }

    @Transactional
    public String deleteComment(Long commentPid, UserDetails userDetails) {
        Comment comment = commentRepository.findById(commentPid).orElseThrow(() -> {
            throw new CustomException(ErrorCode.WRONG_COMMENT_PID);
        });

        if (checkAuthority(comment, userDetails)) {
            commentRepository.delete(comment);
        }

        return "성공!";
    }

    public boolean checkAuthority(Comment comment, UserDetails userDetails) {
        if (UserRoleEnum.ADMIN.toString().equals(userDetails.getAuthorities())) {
            return true;
        } else {
            if (!userDetails.getUsername().equals(comment.getName())) {
                throw new CustomException(ErrorCode.WRONG_NAME);
            }
        }
        return true;
    }


}
