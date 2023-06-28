package com.hanhae.hanhae99.board.model.response;

import com.hanhae.hanhae99.board.model.entity.Comment;

import java.util.List;

public record BoardResponse(
        String title,
        String name,
        String content,
        String createdAt,
        List<Comment> comments
) {
}
