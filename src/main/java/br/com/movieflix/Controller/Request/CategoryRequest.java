package br.com.movieflix.Controller.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "category name is required") String name) {
}
