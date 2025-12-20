package br.com.movieflix.Controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(name = "email", description = "user email")
        String email,
        @Schema(name = "password", description = "user pass")
        String password) {
}
