package capstone.data;

<<<<<<< HEAD
public class UserRepository {
=======
import capstone.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
    User add(User user);
    boolean update(User user);
    boolean deleteByUsername(String username);
>>>>>>> 15301132532732ede2e3b12c0682f9139bc5850e

}
