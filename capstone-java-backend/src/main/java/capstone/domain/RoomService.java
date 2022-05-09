package capstone.domain;

import capstone.data.RoomRepository;
import capstone.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findByUserId(int AppUserId) {
        return roomRepository.findByUserId(AppUserId);
    }

    public Room findByRoomId(int roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public Room findByRoomName(String name) {
        return roomRepository.findByRoomName(name);
    }

    public boolean addRoomhasUser(int roomId, int userId) {
        if(roomRepository.addRoomHasUser(roomId, userId)) return true;

        return false;
    }

    public Result<Room> add(Room room) {
        Result<Room> result = validate(room);
        if (!result.isSuccess()) {
            return result;
        }
        room = roomRepository.add(room);
        result.setPayload(room);
        return result;
    }

    public Result<Room> update(Room room) {
        Result<Room> result = validate(room);

        if (room.getRoomId() < 1) {
            result.addMessage("Room id invalid", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }
        if (room.getRoomId() < 1) {
            result.addMessage("Need a room Id for update operation", ResultType.INVALID);
        }
        if (!roomRepository.update(room)) {
            result.addMessage("Room not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteByRoomId(int roomId) {
        return roomRepository.deleteByRoomId(roomId);
    }

    private Result<Room> validate(Room room) {
        Result<Room> result = new Result<>();
        if (room.getRoomName() == (null)) {
            result.addMessage("Room name cannot be null", ResultType.INVALID);
            return result;
        }
        if (room.getRoomName().equals("")) {
            result.addMessage("Room name cannot be empty", ResultType.INVALID);
        }
        return result;
    }
}
