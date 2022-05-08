package capstone.controllers;

import capstone.models.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class MessageControllerTest {
    @Autowired
    MessageController messageController;
    @Test
    void shouldFindAll() {
        List<Message> result = messageController.findAll();
        assertTrue(result.size() > 0);
    }

    @Test
    void shouldFindById() {
        int iDToFindBy = 1;
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
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}