package data.mappers;
import org.springframework.jdbc.core.RowMapper;
import models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(250) NOT NULL,
  `password_hash` VARCHAR(250) NOT NULL,
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        return user;
    }
}
