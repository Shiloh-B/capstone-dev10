package capstone.data;

import capstone.models.AppUser;

public interface UserRepository {
//    List<AppUser> findAll();
    AppUser findByUsername(String username);
    AppUser add(AppUser appUser);
    boolean update(AppUser appUser);
    boolean addToken(String token);
//    boolean deleteByUsername(String username);

}
