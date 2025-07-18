package br.com.movieflix.Controller.Response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
