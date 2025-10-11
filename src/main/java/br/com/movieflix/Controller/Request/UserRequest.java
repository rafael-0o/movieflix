package br.com.movieflix.Controller.Request;

import lombok.Builder;

@Builder
public record UserRequest(String name, String password, String email) {
}
