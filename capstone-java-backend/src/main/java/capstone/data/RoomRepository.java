package capstone.data;

import capstone.models.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository {
    List<Room> findByUserId(int AppUserId);
    Room findById(int roomId);
    Room add(Room room);
    Room update(Room room);
    @Transactional
    boolean deleteByRoomId(int roomId);
}
