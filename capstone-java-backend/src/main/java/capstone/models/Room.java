package capstone.models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomId;
    private String roomName;

    private List<AppUser> userList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();
    public Room(int roomId, String roomName, List<AppUser> userList, List<Message> messageList) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.userList = userList;
        this.messageList = messageList;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    //May need equals although not 100% certain as of now
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && roomName.equals(room.roomName);
    }

}
