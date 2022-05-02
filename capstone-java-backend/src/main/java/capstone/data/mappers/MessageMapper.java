package capstone.data.mappers;

import capstone.models.Message;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Message(
                resultSet.getInt("message_id"),
                resultSet.getString("message"),
                resultSet.getTimestamp("timestamp"),
                resultSet.getInt("room_id"),
                resultSet.getInt("user_id")
        );
    }
}

