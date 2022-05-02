package capstone.domain;

import capstone.data.MessageRepository;
import capstone.models.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService service;

    @MockBean
    MessageRepository repository;

    @Test
    void shouldFindMessage() {
        Message expected = makeMessage();
        when(repository.findById(1)).thenReturn(expected);
        Message actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Message message = makeMessage();
        Result<Message> result = service.add(message);
        assertEquals(ResultType.INVALID, result.getType());

        message.setMessageId(0);
        message.setRoomId(0);
        message.setUserId(0);
        result = service.add(message);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenValid() {
        Message expected = makeMessage();
        Message arg = makeMessage();
        arg.setMessageId(0);

        when(repository.add(arg)).thenReturn(expected);
        Result<Message> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    Message makeMessage() {
        Message message = new Message();
        message.setMessageContent("TEST");
        message.setTimeStamp(Timestamp.valueOf("2022-04-30 12:12:12"));
        message.setRoomId(1);
        message.setUserId(1);
        return message;
    }
}