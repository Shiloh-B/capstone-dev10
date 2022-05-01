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

    public Message findById(int messageId) {
        return messageRepository.findById(messageId);
    }


}
