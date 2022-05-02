package capstone.data;

import capstone.data.mappers.RoomMapper;
import capstone.models.Room;
import org.springframework.jdbc.core.JdbcTemplate;

public class RoomTemplateRepository implements RoomRepository{

    private final JdbcTemplate jdbcTemplate;

    public RoomTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Room findById(int roomId) {
        final String sql = "select * from room where room_id = ?";
        Room room = jdbcTemplate.query(sql, new RoomMapper(), roomId).stream().findFirst().orElse(null);
        return room;
    }

    @Override
    public Room add(Room room) {
        return null;
    }

    @Override
    public Room update(int roomName) {
        return null;
    }

    @Override
    public boolean deleteByRoomId(int roomId) {
        return false;
    }
}
