package br.com.movieflix.Controller.Request;

import lombok.Builder;

@Builder
public record CategoryRequest(String name) {
}
