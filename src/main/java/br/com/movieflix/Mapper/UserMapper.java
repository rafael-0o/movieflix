package br.com.movieflix.Mapper;

import br.com.movieflix.Controller.Request.UserRequest;
import br.com.movieflix.Controller.Response.UserResponse;
import br.com.movieflix.Entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(UserRequest userRequest){
        return User
                .builder()
                .name(userRequest.name())
                .password(userRequest.password())
                .email(userRequest.email())
                .build();
    }
    public static UserResponse toUserResponse(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
