package data;

import data.mappers.MessageMapper;
import models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class MessageJdbcTemplateRepository implements MessageRepository{

    private final JdbcTemplate jdbcTemplate;

    public MessageJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> findAll() {
        final String sql = "select message_id, message, timestamp, room_id, user_id "
                + "from message limit 1000;";
        return jdbcTemplate.query(sql, new MessageMapper());
    }

    @Override
    public Message findById(int messageId) {
        final String sql = "select message_id, message, timestamp, room_id, user_id "
                + "from message "
                + "where message_id = ?;";

        Message message = jdbcTemplate.query(sql, new MessageMapper(), messageId).stream()
                .findFirst().orElse(null);

        return message;
    }

    @Override
    public Message add(Message message) {
        final String sql = "insert into message (message, timestamp, room_id, user_id) "
                + " values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getMessageContent());
            ps.setTimestamp(2, message.getTimeStamp());
            ps.setInt(3, message.getRoomId());
            ps.setInt(4, message.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        message.setMessageId(keyHolder.getKey().intValue());
        return message;
    }

    @Override
    public boolean update(Message message) {
        final String sql = "update message set "
                + "message = ?, "
                + "timestamp = ?, "
                + "room_id = ?, "
                + "user_id = ? "
                + "where message_id = ?;";

        return jdbcTemplate.update(sql,
                message.getMessageContent(),
                message.getTimeStamp(),
                message.getRoomId(),
                message.getUserId(),
                message.getMessageId()) > 0;
    }

    @Override
    public boolean deleteById(int messageId) {
        return jdbcTemplate.update("delete from message where message_id = ?;", messageId) > 0;
    }
}
