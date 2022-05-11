package capstone.controllers;

import capstone.models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomControllerTest {
    @Autowired
    RoomController roomController;

    @Test
    void shouldFindByUserId() {
        int appUserId = 1;
        ResponseEntity<List<Room>> responseEntity = roomController.findByUserId(appUserId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().size());
    }

    @Test
    void shouldNotFindByNotExistingUserId() {
        int notFindableAppUserId = 100;
        ResponseEntity<List<Room>> responseEntity = roomController.findByUserId(notFindableAppUserId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void shouldFindByRoomId() {
        int roomId = 1;
        ResponseEntity<Room> responseEntity = roomController.findByRoomId(roomId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotFindByNotExistingRoomId() {
        int notFindableRoomId = 100;
        ResponseEntity<Room> responseEntity = roomController.findByRoomId(notFindableRoomId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    void shouldAddValid() {
        Room toAdd = makeRoom();
        ResponseEntity<Room> responseEntity = roomController.add(toAdd);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldNotAddInvalidRoom() {
        Room dontAdd = makeRoom();
        dontAdd.setRoomName("");
        ResponseEntity<Room> responseEntity = roomController.add(dontAdd);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void shouldUpdate() {
        Room toUpdate = makeRoom();
        ResponseEntity<Room> responseEntity = roomController.update(toUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenUpdate() {
        Room toUpdate = makeRoom();
        toUpdate.setRoomId(0);
        ResponseEntity<Room> responseEntity = roomController.update(toUpdate);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenUpdate() {
        Room toUpdate = new Room();
        toUpdate.setRoomName("room");
        toUpdate.setRoomId(100);
        ResponseEntity<Room> responseEntity = roomController.update(toUpdate);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    void shouldNotDeleteInvalid() {
        Room toDelete = makeRoom();
        toDelete.setRoomId(-10);
        ResponseEntity<Void> responseEntity = roomController.delete(toDelete.getRoomId());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private Room makeRoom() {
        Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("test room");
        return room;
    }
}