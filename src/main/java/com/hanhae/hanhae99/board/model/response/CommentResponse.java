package com.hanhae.hanhae99.board.model.response;

import com.hanhae.hanhae99.board.model.entity.Board;
import com.hanhae.hanhae99.board.model.entity.CommentHeart;

import java.util.List;

public record CommentResponse(
        String name,
        String comment,
        long heart
) {
}
