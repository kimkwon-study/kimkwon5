package com.hanhae.hanhae99.model.response;

public record BoardResponse(
        String title,
        String name,
        String content,
        String createdAt
) {
}
