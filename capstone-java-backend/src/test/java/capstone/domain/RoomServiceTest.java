package capstone.domain;

import capstone.data.RoomRepository;
import capstone.models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoomServiceTest {

    @Autowired
    RoomService service;

    @MockBean
    RoomRepository repository;

    @Test
    void findByUserId() {
    }


    @Test
    void findByRoomId() {
        Room expected = makeRoom();
        when(repository.findByRoomId(1)).thenReturn(expected);
        Room actual = service.findByRoomId(1);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteByRoomId() {
    }

    private Room makeRoom() {
        Room room = new Room();
        room.setRoomName("test room");
        return  room;
    }
}