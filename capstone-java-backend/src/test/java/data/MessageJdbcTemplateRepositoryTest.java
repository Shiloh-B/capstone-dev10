package data;

import capstone.data.MessageJdbcTemplateRepository;
import capstone.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 5;

    @Autowired
    MessageJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Message> messages = repository.findAll();
        assertNotNull(messages);
        // if delete is first, we're down to 3
        // if add is first, we may go as high as 6
        assertTrue(messages.size() >= 3 && messages.size() <= 6);
    }

    @Test
    void shouldFindHelloWorld() {
        Message hello = repository.findById(1);
        assertEquals(1, hello.getMessageId());
        assertEquals("Hello World!", hello.getMessageContent());
        assertEquals("04/23/17 04:34:22", hello.getTimeStamp().toString());
        assertEquals(1, hello.getRoomId());
        assertEquals(1, hello.getUserId());
    }

    @Test
    void shouldAdd() {
        Message message = makeMessage();
        Message actual = repository.add(message);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getMessageId());
    }

    @Test
    void shouldUpdate() {
        Message message = makeMessage();
        message.setMessageId(3);
        assertTrue(repository.update(message));
        message.setMessageId(13);
        assertFalse(repository.update(message));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Message makeMessage() {
        Message message = new Message();
        message.setMessageContent("TEST");
        message.setTimeStamp(Timestamp.valueOf("04/23/17 04:34:22"));
        message.setRoomId(1);
        message.setUserId(1);
        return message;
    }
}