package br.com.movieflix.Controller;

import br.com.movieflix.Config.TokenService;
import br.com.movieflix.Controller.Request.LoginRequest;
import br.com.movieflix.Controller.Request.UserRequest;
import br.com.movieflix.Controller.Response.LoginResponse;
import br.com.movieflix.Controller.Response.UserResponse;
import br.com.movieflix.Entity.User;
import br.com.movieflix.Exeption.Username0rPasswordInvalidExeption;
import br.com.movieflix.Mapper.UserMapper;
import br.com.movieflix.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movieflix/auth")
@Tag(name = "Authentication", description = "resource responsible for managing authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    @Operation(summary = "register user", description = "create a new user")
    @ApiResponse(responseCode = "201", description = "user created",
    content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @PostMapping("/register")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        User nUser= userService.register(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(nUser));

    }
    @Operation(summary = "Login", description = "create a new login")
    @ApiResponse(responseCode = "201", description = "login created",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),loginRequest.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);
            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e){
            throw new Username0rPasswordInvalidExeption("user or password invalid");
        }


    }
}
