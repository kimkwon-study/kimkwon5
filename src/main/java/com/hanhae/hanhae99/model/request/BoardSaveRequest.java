package com.hanhae.hanhae99.model.request;

import jakarta.validation.constraints.NotNull;

public record BoardSaveRequest(
        @NotNull
        String title,
        @NotNull
        String name,
        @NotNull
        String password,
        @NotNull
        String content
) {
}
