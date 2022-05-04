package capstone.domain;

import capstone.data.RoomRepository;
import capstone.models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void shouldAdd() {
        Room toAdd = makeRoom();
        when(repository.add(toAdd)).thenReturn(toAdd);
        Result<Room> result = service.add(toAdd);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(toAdd, result.getPayload());
    }

    @Test
    void shouldFailToAddInvalid(){
        Room dontAdd = makeRoom();
        dontAdd.setRoomId(-2);
        dontAdd.setRoomName("");
        Result<Room> result = service.add(dontAdd);
        assertNotEquals(ResultType.SUCCESS, result.getType());
    }
    @Test
    void update() {
    }

    @Test
    void deleteByRoomId() {
    }

    private Room makeRoom() {
        Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("test room");
        return  room;
    }
}