package capstone.data;

import capstone.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class RoomTemplateRepositoryTest {

    @Autowired
    KnownGoodState knownGoodState;
    @Autowired
    RoomTemplateRepository repository;
    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByUserId() {
        List<Room> resultList = repository.findByUserId(1);
        assertTrue(resultList.size()>0);
    }

    @Test
    void findByRoomId() {
        Room result = repository.findByRoomId(1);
        System.out.println(result.getRoomName());
        assertTrue(result.getRoomName().equalsIgnoreCase("test room"));
    }

    @Test
    void add() {
        Room toAdd = new Room(0, "nik");
        Room result = repository.add(toAdd);
        assertEquals(result, toAdd);
    }

    @Test
    void update() {
        Room toUpdate = new Room(2, "test room update");
        assertTrue(repository.update(toUpdate));
        assertEquals("test room update", repository.findByRoomId(2).getRoomName());

    }

    @Test
    void deleteByRoomId() {
        assertTrue(repository.deleteByRoomId(4));
        assertFalse(repository.deleteByRoomId(7));
    }
}