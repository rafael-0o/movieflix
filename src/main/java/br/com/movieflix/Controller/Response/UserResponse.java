package br.com.movieflix.Controller.Response;

import lombok.Builder;

@Builder
public record UserResponse(Long id,String name, String email) {
}
