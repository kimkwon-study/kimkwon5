package com.hanhae.hanhae99.board.model.response;

import com.hanhae.hanhae99.board.model.entity.Board;

public record CommentResponse(
        String name,
        String comment,
        BoardResponse board
) {
}
