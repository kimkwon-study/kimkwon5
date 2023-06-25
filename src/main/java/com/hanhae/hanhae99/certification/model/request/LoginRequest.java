package com.hanhae.hanhae99.certification.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull
        @NotBlank
        String id,
        @NotNull
        @NotBlank
        String pw
) {
}
