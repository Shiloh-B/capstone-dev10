package capstone.data;

import capstone.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageJdbcTemplateRepositoryTest {

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
        assertTrue(messages.size() > 0);
    }

    @Test
    void shouldFindHelloWorld() {
        Message hello = repository.findById(3);

        assertNotNull(hello);
        assertEquals(hello.getMessageContent(), "test3");
    }

    @Test
    void shouldFindByUserId() {
        List<Message> messages = repository.findByUserId(1);
        assertTrue(messages.size() > 0);
    }

    @Test
    void shouldAdd() {
        Message message = makeMessage();
        Message actual = repository.add(message);
        assertNotNull(actual);
        assertEquals("TEST", actual.getMessageContent());
    }

    @Test
    void shouldUpdate() {
        Message message = makeMessage();
        message.setMessageId(1);
        message.setMessageContent("new test");
        assertTrue(repository.update(message));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Message makeMessage() {
        Message message = new Message();
        message.setMessageContent("TEST");
        message.setTimeStamp(Timestamp.valueOf("2022-04-30 12:12:12"));
        message.setRoomId(1);
        message.setUserId(1);
        return message;
    }
}