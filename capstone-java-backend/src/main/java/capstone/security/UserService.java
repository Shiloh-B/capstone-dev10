package capstone.security;

import capstone.data.UserRepository;
import capstone.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null || user.isDisabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return user;
    }

    public User add(String username, String password) {
        validate(username);
        validatePassword(password);

        password = encoder.encode(password);

        User user = new User(0, username, password, false, List.of("User"));

        return repository.add(user);
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }

        if (username.length() > 50) {
            throw new ValidationException("username must be less than 50 characters");
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException("password is required");
        }
    }

}
