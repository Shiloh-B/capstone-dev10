package capstone.data;

import capstone.models.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository {
    List<Room> findByUserId(int AppUserId);
    Room findByRoomId(int roomId);
    Room findByRoomName(String name);
    boolean addRoomHasUser(int roomId, int userId);
    Room add(Room room);
    @Transactional
    boolean update(Room room);
    @Transactional
    boolean deleteByRoomId(int roomId);
}
