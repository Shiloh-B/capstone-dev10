package capstone.data;

import capstone.models.Message;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface MessageRepository {
    List<Message> findAll();

    Message findById(int messageId);

    List<Message> findByRoomId(int roomId);

    Message add(Message message);

    boolean update(Message message);

    boolean deleteById(int messageId);

    List<Message> findByUserId(int userId);

    Message findByUsernameAndMessage(String username, String message);
}
