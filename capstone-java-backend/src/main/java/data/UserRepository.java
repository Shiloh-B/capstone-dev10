package data;

import models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int userID);
    User add(User user);
    boolean update();
    boolean deleteById(int userID);

}
