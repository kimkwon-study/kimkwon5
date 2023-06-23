package com.hanhae.hanhae99.model.request;

public record BoardSaveRequest(
        String title,
        String name,
        String password,
        String content
) {
}
