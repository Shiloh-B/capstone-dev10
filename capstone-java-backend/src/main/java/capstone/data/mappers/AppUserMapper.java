package capstone.data.mappers;
import org.springframework.jdbc.core.RowMapper;
import capstone.models.AppUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {

    private final List<String> roles;

    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }
    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {

        return new AppUser(
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password_hash"),
                resultSet.getBoolean("disabled"),
                roles
        );
    }
}
