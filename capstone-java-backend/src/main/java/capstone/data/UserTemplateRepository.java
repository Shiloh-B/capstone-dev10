package capstone.data;

import capstone.data.mappers.UserMapper;
import capstone.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserTemplateRepository implements  UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select user_id, username, password_hash, disabled from `chat_app`.`user` limit 1000";
         return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "select user_id, username, password_hash, disabled " +
                "from `chat_app`.`user`"+
                "where username = ?";
        User user = jdbcTemplate.query(sql, new UserMapper(), username).stream()
                .findFirst().orElse(null);
        if(user != null){
            //TODO RoomUser list here
        }
        return user;
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user(username, password_hash, disabled) " +
                "values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setBoolean(3, user.getIsDisabled());
            return ps;
        }, keyHolder);
        if(rowsAffected==0) {
            return null;
        }
        user.setUserID(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user " +
                "set " +
                "username = ?, "+ "password_hash = ?, " + "disabled = ? " +
                "where user_id = ?;";
         return jdbcTemplate.update(sql,
                 user.getUsername(),
                 user.getPasswordHash(),
                 user.getIsDisabled(),
                 user.getUserID())> 0;
    }

    @Override
    public boolean deleteByUsername(String username) {
        return  jdbcTemplate.update("delete from user where username= ?;", username) > 0;
    }
}
