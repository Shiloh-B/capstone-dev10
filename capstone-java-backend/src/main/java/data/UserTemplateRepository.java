package data;

import models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserTemplateRepository implements  UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int userID) {
        return null;
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean deleteById(int userID) {
        return false;
    }
}
