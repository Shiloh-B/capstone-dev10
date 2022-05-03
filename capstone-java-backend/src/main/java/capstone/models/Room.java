package capstone.models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomId;
    private String roomName;
    private List<AppUser> userList; //List of appusers, e.g. (1, "nik", password_hash, false, roles)
    private List<Message> messageList; //List of messages, e.g. "(1, "hello", timestamp, 1, 1)"

    public Room(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public Room() {
        //default constructor
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
    public List<AppUser> getUserList() {
        return userList;
    }

    public void setUserList(List<AppUser> userList) {
        this.userList = userList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && roomName.equals(room.roomName);
    }

}
