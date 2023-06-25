package com.hanhae.hanhae99.board.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record BoardSaveRequest(
        @NotNull
        @NotBlank
        @NotEmpty
        String title,

        @NotNull
        @NotBlank
        @NotEmpty
        String name,

        @NotNull
        @NotBlank
        @NotEmpty
        String password,

        @NotNull
        @NotBlank
        @NotEmpty
        String content
) {
}
