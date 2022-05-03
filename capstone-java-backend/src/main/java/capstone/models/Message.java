package capstone.models;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private String messageContent;
    private long timestamp;
    private int roomId;
    private int userId;
    private String username;

    public Message(int messageId, String messageContent, long timestamp, int roomId, int userId, String username) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
        this.roomId = roomId;
        this.userId = userId;
        this.username = username;
    }

    public Message() {
        // default constructor
    }


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

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timestamp = timeStamp;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                ", timestamp=" + timestamp +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
