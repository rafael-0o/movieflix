package br.com.movieflix.Controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @Schema(type = "String", description = "Streaming name")
        @NotEmpty(message = "name is required") String name) {
}
