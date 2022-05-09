package capstone.controllers;

import capstone.data.KnownGoodState;
import capstone.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageControllerTest {
    @Autowired
    MessageController messageController;
    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Message> result = messageController.findAll();
        assertTrue(result.size() > 0);
    }

    @Test
    void shouldFindById() {
        int iDToFindBy = 4;
        ResponseEntity<Message> result = messageController.findById(iDToFindBy);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldNotFindByUnusedId() {
        int iDToNotFindBy = 100;
        ResponseEntity<Message> result = messageController.findById(iDToNotFindBy);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void findByRoomId() {
        int mainRoomId = 1; //because should be only one room as of now
        List<Message> result = messageController.findByRoomId(mainRoomId);
        assertTrue(result.size() > 0);
    }

    @Test
    void shouldAdd() {
        Message toAdd = makeMessage();
        toAdd.setUsername("nik");
        ResponseEntity<Message> responseEntity = messageController.add(toAdd);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotAddInvalidMessage() {
        Message toNotAdd = makeMessage();
        toNotAdd.setMessageId(1); // Cannot set message id
        Message blankMessageToNotAdd = makeMessage();
        blankMessageToNotAdd.setMessageContent(""); // cannot be blank
        Message messageWithoutRoomId = makeMessage();
        messageWithoutRoomId.setRoomId(0); // must set Room Id to add message
        ResponseEntity<Message> responseEntityOne = messageController.add(toNotAdd);
        ResponseEntity<Message> responseEntityTwo = messageController.add(blankMessageToNotAdd);
        ResponseEntity<Message> responseEntityThree = messageController.add(messageWithoutRoomId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntityOne.getStatusCode()); //error caught in add service
        assertEquals(HttpStatus.BAD_REQUEST, responseEntityTwo.getStatusCode()); // error caught in validate service
        assertEquals(HttpStatus.BAD_REQUEST, responseEntityThree.getStatusCode()); // error caught in validate service
    }

    @Test
    void shouldUpdate() {
        Message toUpdate = makeMessage();
        toUpdate.setUsername("nik");
        toUpdate.setMessageId(1);
        int id = 1;
        ResponseEntity<Message> responseEntity = messageController.update(toUpdate, id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotUpdate() {
        // Conflict thrown via controller and bad request thrown via service
        int id = 1;
        Message toConflict = makeMessage();
        toConflict.setMessageId(2);
        ResponseEntity<Message> conflictResponseEntity = messageController.update(toConflict, id);
        assertEquals(HttpStatus.CONFLICT, conflictResponseEntity.getStatusCode());

        Message toBadRequest = makeMessage();
        toBadRequest.setMessageId(1);
        toBadRequest.setRoomId(-1);
        toBadRequest.setUsername("nik");
        ResponseEntity<Message> badRequestResponseEntity = messageController.update(toBadRequest, id);
        assertEquals(HttpStatus.BAD_REQUEST, badRequestResponseEntity.getStatusCode());
    }

    @Test
    void shouldDelete() {
        ResponseEntity<Void> responseEntity = messageController.deleteById(4);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotDelete() {
        ResponseEntity<Void> responseEntity = messageController.deleteById(-10);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private Message makeMessage() {
        Message message = new Message();
        message.setMessageContent("TEST");
        message.setTimeStamp(Timestamp.valueOf("2022-05-03 12:12:12"));
        message.setRoomId(1);
        message.setUserId(1);
        return message;
    }
}