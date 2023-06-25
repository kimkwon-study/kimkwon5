package com.hanhae.hanhae99.certification.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @Size(min = 4, max = 10)
        @NotNull
        @Pattern(regexp = "^[a-z0-9]+$")
        @NotBlank
        String id,
        @Size(min = 8, max = 15)
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9]+$")
        @NotBlank
        String pw,
        @NotNull
        @NotBlank
        String name


) {
}
