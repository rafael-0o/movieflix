package br.com.movieflix.Service;

import br.com.movieflix.Entity.User;
import br.com.movieflix.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return  userRepository.save(user);
    }
}
