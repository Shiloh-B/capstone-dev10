package capstone.data;

import capstone.models.AppUser;

public interface AppUserRepository {
//    List<AppUser> findAll();
    AppUser findByUsername(String username);
    AppUser add(AppUser appUser);
    boolean update(AppUser appUser);
//    boolean deleteByUsername(String username);

}
