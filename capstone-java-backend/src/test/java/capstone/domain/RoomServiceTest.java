package capstone.domain;

import capstone.data.RoomRepository;
import capstone.models.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
        when(repository.findByUserId(1)).thenReturn(List.of(makeRoom()));
        List<Room> list = service.findByUserId(1);
        assertTrue(list.size()>0);
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
    void shouldUpdate() {
        Room toUpdate = makeRoom();
        toUpdate.setRoomName("updated");
        when(repository.update(toUpdate)).thenReturn(true);
        Result<Room> actual = service.update(toUpdate);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateInvalid(){
        Room dontUpdate = makeRoom();
        dontUpdate.setRoomName("");
        Result<Room> actual = service.update(dontUpdate);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    @Test
    void deleteByRoomId() {
        when(repository.deleteByRoomId(1)).thenReturn(true);
        assertTrue(service.deleteByRoomId(1));
    }

    private Room makeRoom() {
        Room room = new Room();
        room.setRoomId(1);
        room.setRoomName("test room");
        return  room;
    }
}