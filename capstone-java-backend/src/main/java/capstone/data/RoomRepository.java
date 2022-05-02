package capstone.data;

import capstone.models.Room;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository {
    Room findById(int roomId);
    Room add(Room room);
    Room update(int roomName);
    @Transactional
    boolean deleteByRoomId(int roomId);
}
