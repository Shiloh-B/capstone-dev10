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
    // not really necessary but eh
    }


    @Test
    void shouldAdd() {
        Message message = makeMessage();

        when(repository.add(message)).thenReturn(message);

        Result<Message> actual = service.add(message);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(message, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Message message = makeMessage();

        message.setMessageId(5);
        Result<Message> result = service.add(message);
        assertEquals(ResultType.INVALID, result.getType());

        message.setRoomId(0);
        result = service.add(message);
        assertEquals(ResultType.INVALID, result.getType());

        message.setUserId(0);
        result = service.add(message);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        Message message = makeMessage();
        message.setMessageId(2);

        when(repository.update(message)).thenReturn(true);
        Result<Message> actual = service.update(message);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Message message = makeMessage();
        message.setMessageId(30);

        when(repository.update(message)).thenReturn(false);
        Result<Message> actual = service.update(message);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Message message = makeMessage();
        message.setMessageId(2);

        message.setRoomId(0);
        Result<Message> actual = service.update(message);
        assertEquals(ResultType.INVALID, actual.getType());

        message.setUserId(0);
        actual = service.update(message);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    Message makeMessage() {
        Message message = new Message();
        message.setMessageContent("TEST");
        message.setTimeStamp(Timestamp.valueOf("2022-05-03 12:12:12"));
        message.setRoomId(1);
        message.setUserId(1);
        return message;
    }
}