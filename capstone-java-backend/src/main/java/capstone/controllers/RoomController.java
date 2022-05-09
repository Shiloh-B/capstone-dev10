package capstone.controllers;

import capstone.domain.Result;
import capstone.domain.ResultType;
import capstone.domain.RoomService;
import capstone.models.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/user/{AppUserId}")
    public ResponseEntity<List<Room>> findByUserId(@PathVariable int AppUserId) {
        List<Room> list = roomService.findByUserId(AppUserId);
        if (list == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> findByRoomId(@PathVariable int roomId) {
        Room room = roomService.findByRoomId(roomId);
        if (room == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/name/{roomName}")
    public ResponseEntity<Room> findByRoomName(@PathVariable String roomName) {
        Room room = roomService.findByRoomName(roomName);
        if(room == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> add(@RequestBody Room room) {
        Result<Room> roomResult = roomService.add(room);
        if (!roomResult.isSuccess()) {
            return new ResponseEntity<>(roomResult.getPayload(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(roomResult.getPayload(), HttpStatus.OK);
    }

    @PostMapping("/roomhasuser/{roomId}/{userId}")
    public boolean addRoomHasUser(@PathVariable int roomId, @PathVariable int userId) {
        if(roomService.addRoomhasUser(roomId, userId)) return true;

        return false;
    }

    @PutMapping
    public  ResponseEntity<Room> update(@RequestBody Room room){
        Result<Room> roomResult = roomService.update(room);
        if(roomResult.getType()== ResultType.INVALID){
            return new ResponseEntity<>(roomResult.getPayload(), HttpStatus.BAD_REQUEST);
        }
        if(roomResult.getType()==ResultType.NOT_FOUND){
            return  new ResponseEntity<>(roomResult.getPayload(), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(roomResult.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> delete(@PathVariable int roomId){
        if(!roomService.deleteByRoomId(roomId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
