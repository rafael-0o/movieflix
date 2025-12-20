package br.com.movieflix.Controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @Schema(type = "String", description = "category name")
        @NotEmpty(message = "category name is required") String name) {
}
