package capstone.data;

import capstone.models.AppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface UserRepository {
//    List<AppUser> findAll();
    AppUser findByUsername(String username);
    AppUser add(AppUser appUser);
    boolean update(AppUser appUser);
    boolean addToken(String token);
//    boolean deleteByUsername(String username);

}
