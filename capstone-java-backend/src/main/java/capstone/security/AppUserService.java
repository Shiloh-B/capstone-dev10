package capstone.security;

import capstone.data.MessageRepository;
import capstone.data.UserRepository;
import capstone.models.AppUser;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository repository;
    private final MessageRepository messageRepository;
    private final PasswordEncoder encoder;

    public AppUserService(UserRepository repository,
                          MessageRepository messageRepository,
                          PasswordEncoder encoder) {
        this.messageRepository = messageRepository;
        this.repository = repository;
        this.encoder = encoder;
    }

    public boolean addToken(String token) {
        return repository.addToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        // add messages to user
        appUser.setMessages(messageRepository.findByUserId(appUser.getAppUserId()));

        return appUser;
    }

    public AppUser add(String username, String password) {
        validate(username);
        validatePassword(password);

        password = encoder.encode(password);

        AppUser appUser = new AppUser(0, username, password, false, List.of("USER"));

        return repository.add(appUser);
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }

        if (username.length() > 50) {
            throw new ValidationException("username must be less than 50 characters");
        }


        if(repository.findByUsername(username) != null) {
            throw new DuplicateKeyException("username must be unique");
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException("password is required");
        }
    }

}