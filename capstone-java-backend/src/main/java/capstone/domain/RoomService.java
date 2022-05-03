package capstone.domain;

import capstone.data.RoomRepository;
import capstone.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private  final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findByUserId(int AppUserId){
        return  roomRepository.findByUserId(AppUserId);
    }
    public Room findByRoomId(int roomId){
        return  roomRepository.findByRoomId(roomId);
    }
    public Result<Room> add(Room room){
        Result<Room> result = validate(room);
        return  result;
    }
    public Result<Room> update(Room room){
        Result<Room> result = validate(room);
        return  result;
    }
    public boolean deleteByRoomId(int roomId){
        return roomRepository.deleteByRoomId(roomId);
    }
    private Result<Room> validate(Room room){
        Result<Room> result = new Result<>();
        return  result;
    }
}
