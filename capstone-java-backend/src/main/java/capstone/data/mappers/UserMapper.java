package capstone.data.mappers;
import org.springframework.jdbc.core.RowMapper;
import capstone.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        //TODO eventually add Roles
        return new User(
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password_hash"),
                resultSet.getBoolean("disabled")
        );
    }
}
