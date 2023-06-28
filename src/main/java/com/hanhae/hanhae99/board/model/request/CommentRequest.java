package com.hanhae.hanhae99.board.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotNull
        @NotBlank
        String content
) {
}
