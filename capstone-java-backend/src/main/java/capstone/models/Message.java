package capstone.models;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private String messageContent;
    private Timestamp timeStamp;
    private int roomId;
    private int userId;

    public Message(int messageId, String messageContent, Timestamp timeStamp, int roomId, int userId) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.timeStamp = timeStamp;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Message() {
        // default constructor
    }

//    private Room room;
//    private User user;


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
    // dk if room and user objects are needed here


}