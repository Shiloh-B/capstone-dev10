package capstone.data;

import capstone.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
    User add(User user);
    boolean update(User user);
    boolean deleteByUsername(String username);

}
