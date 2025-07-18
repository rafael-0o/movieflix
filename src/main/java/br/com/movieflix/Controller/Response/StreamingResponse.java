package br.com.movieflix.Controller.Response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
