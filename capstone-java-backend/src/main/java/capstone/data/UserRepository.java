package capstone.data;

import capstone.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int userID);
    User add(User user);
    boolean update(User user);
    boolean deleteById(int userID);

}
