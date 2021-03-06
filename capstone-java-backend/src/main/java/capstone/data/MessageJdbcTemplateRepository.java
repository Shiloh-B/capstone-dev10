package capstone.data;

import capstone.data.mappers.MessageMapper;
import capstone.models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class MessageJdbcTemplateRepository implements MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessageJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> findAll() {
        final String sql = "select message_id, message, timestamp, room_id, user_id, username "
                + "from message limit 1000;";
        return jdbcTemplate.query(sql, new MessageMapper());
    }

    @Override
    public Message findById(int messageId) {
        final String sql = "select message_id, message, timestamp, room_id, user_id, username "
                + "from message "
                + "where message_id = ?;";

        Message message = (Message) jdbcTemplate.query(sql, new MessageMapper(), messageId).stream()
                .findFirst().orElse(null);

        return message;
    }

    public List<Message> findByUserId(int userId) {
        final String sql = "select message_id, message, timestamp, room_id, user_id, username "
                + "from message "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new MessageMapper(), userId);
    }

    @Override
    public Message findByUsernameAndMessage(String username, String message) {
        final String sql = "select message_id, message, timestamp, room_id, user_id, username " +
                "from message " +
                "where message = ? and username = ?;";

        Message foundMessage = (Message) jdbcTemplate.query(sql, new MessageMapper(), message, username)
                .stream().findFirst().orElse(null);

        return foundMessage;
    }

    public List<Message> findByRoomId(int roomId) {
        final String sql = "select message_id, message, timestamp, room_id, user_id, username "
                + "from message "
                + "where room_id = ? "
                + "order by timestamp;";

        return jdbcTemplate.query(sql, new MessageMapper(), roomId);
    }

    @Transactional
    @Override
    public Message add(Message message) {
        final String sql = "insert into message (message, timestamp, room_id, user_id, username) "
                + " values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getMessageContent());
            ps.setTimestamp(2, message.getTimeStamp());
            ps.setInt(3, message.getRoomId());
            ps.setInt(4, message.getUserId());
            ps.setString(5, message.getUsername());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        message.setMessageId(keyHolder.getKey().intValue());
        return message;
    }

    @Transactional
    @Override
    public boolean update(Message message) {
        final String sql = "update message set "
                + "message = ?, "
                + "timestamp = ?, "
                + "room_id = ?, "
                + "user_id = ?, "
                + "username = ? "
                + "where message_id = ?;";

        return jdbcTemplate.update(sql,
                message.getMessageContent(),
                message.getTimeStamp(),
                message.getRoomId(),
                message.getUserId(),
                message.getUsername(),
                message.getMessageId()) > 0;
    }

    @Override
    public boolean deleteById(int messageId) {
        return jdbcTemplate.update("delete from message where message_id = ?;", messageId) > 0;
    }
}
