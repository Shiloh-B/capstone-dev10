package capstone.models;

public class Room {

    private int roomId;
    private String roomName;

    //private List<User> userList = new ArrayList<>(); TODO will be AppUser
    // private List<Message> messageList = new ArrayList<>(); TODO will be Message

    public Room(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
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
