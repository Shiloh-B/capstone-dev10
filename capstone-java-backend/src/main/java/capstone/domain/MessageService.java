package capstone.domain;

import capstone.data.MessageRepository;
import capstone.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findByRoomId(int roomId) {
        return messageRepository.findByRoomId(roomId);
    }

    public Message findById(int messageId) {
        return messageRepository.findById(messageId);
    }

    public Result<Message> add(Message message) {
        Result<Message> result = validate(message);
        if (!result.isSuccess()) {
            return result;
        }

        if (message.getMessageId() != 0) {
            result.addMessage("messageId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        message = messageRepository.add(message);
        result.setPayload(message);
        return result;
    }

    public Result<Message> update(Message message) {
        Result<Message> result = validate(message);
        if (!result.isSuccess()) {
            return result;
        }

        if (message.getMessageId() <= 0) {
            result.addMessage("messageId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!messageRepository.update(message)) {
            String msg = String.format("messageId: %s, not found", message.getMessageId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int messageId) {
        return messageRepository.deleteById(messageId);
    }

    private Result<Message> validate(Message message) {
        Result<Message> result = new Result<>();
        if (message == null) {
            result.addMessage("message cannot be null", ResultType.INVALID);
            return result;
        }

        if(message.getMessageContent().trim().equals("")) {
            result.addMessage("Message cannot be empty.", ResultType.INVALID);
        }

        if (message.getRoomId() < 1) {
            result.addMessage("room id is required", ResultType.INVALID);
        }

        if (message.getUserId() < 1) {
            result.addMessage("user id is required", ResultType.INVALID);
        }

        if (message.getTimeStamp() == null) {
            result.addMessage("timestamp is required", ResultType.INVALID);
        }

        return result;
    }
}
