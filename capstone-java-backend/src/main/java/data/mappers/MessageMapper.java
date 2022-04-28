package data.mappers;

import models.Message;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setMessageId(resultSet.getInt("message_id"));
        message.setMessageContent(resultSet.getString("message"));
        message.setRoomId(resultSet.getInt("room_id"));
        message.setUserId(resultSet.getInt("user_id"));
        if (resultSet.getTimestamp("timestamp") != null) {
            message.setTimeStamp(resultSet.getTimestamp("timestamp"));
        }
        return message;
    }
}

//message_id, message, timestamp, room_id, user_id
