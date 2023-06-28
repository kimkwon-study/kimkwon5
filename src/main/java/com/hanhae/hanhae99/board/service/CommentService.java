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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public CommentResponse saveComment(CommentRequest commentRequest, Long boardPid, HttpServletRequest req) {
        Board board = boardRepository.findById(boardPid).orElseThrow(() ->
        {
            throw new CustomException(ErrorCode.WRONG_BOARD_PID);
        });
        Comment comment = commentRepository.save(Comment.getEntity(
                commentRequest,
                board,
                getTokenToUserName(req)));
        return new CommentResponse(
                comment.getName(),
                comment.getContent(),
                Board.changeEntity(comment.getBoard())
        );
    }

    @Transactional
    public CommentResponse updateComment(CommentRequest commentRequest, Long commentPid, HttpServletRequest req) {
        Comment comment = commentRepository.findById(commentPid).orElseThrow(() -> {
            throw new CustomException(ErrorCode.WRONG_COMMENT_PID);
        });
        if (UserRoleEnum.ADMIN.toString().equals(getTokenToRole(req))) {
            comment.setContent(commentRequest.content());
        } else {
            if (checkToken(req, comment.getName())) {
                throw new CustomException(ErrorCode.WRONG_NAME);
            }
            comment.setContent(commentRequest.content());
        }
        return new CommentResponse(comment.getName(), commentRequest.content(), Board.changeEntity(comment.getBoard()));
    }

    @Transactional
    public String deleteComment(Long commentPid, HttpServletRequest req) {
        Comment comment = commentRepository.findById(commentPid).orElseThrow(() -> {
            throw new CustomException(ErrorCode.WRONG_COMMENT_PID);
        });

        if (UserRoleEnum.ADMIN.toString().equals(getTokenToRole(req))) {
            commentRepository.delete(comment);
        } else {
            if (checkToken(req, comment.getName())) {
                throw new CustomException(ErrorCode.WRONG_NAME);
            }
            commentRepository.delete(comment);
        }
        return "성공!";
    }

    public boolean checkToken(HttpServletRequest req, String name) {
        String tokenName = getTokenToUserName(req);

        if (tokenName.equals(name)) {
            return false;
        }

        return true;
    }

    public String getTokenToUserName(HttpServletRequest req) {
        String token = jwtUtil.getTokenFromRequest(req);
        String userName = jwtUtil.getUserInfoFromToken(
                jwtUtil.substringToken(token)
        ).get("sub").toString();
        return userName;
    }

    public String getTokenToRole(HttpServletRequest req) {
        String token = jwtUtil.getTokenFromRequest(req);
        String authority = jwtUtil.getUserInfoFromToken(
                jwtUtil.substringToken(token)
        ).get(jwtUtil.AUTHORIZATION_KEY).toString();
        return authority;
    }

}
