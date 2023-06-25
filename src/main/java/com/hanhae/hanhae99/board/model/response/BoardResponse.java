package com.hanhae.hanhae99.board.model.response;

public record BoardResponse(
        String title,
        String name,
        String content,
        String createdAt
) {
}
