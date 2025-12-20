package br.com.movieflix.Controller.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.naming.NamingEnumeration;

@Builder
public record UserRequest(
        @Schema(name = "user name", description = "name of user")
        String name,
        @Schema(name = "user pass", description = "password of user")
        String password,
        @Schema(name = "user email", description = "email of user")
        String email) {
}
