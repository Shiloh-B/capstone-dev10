package capstone.data;

import capstone.data.mappers.RoomMapper;
import capstone.models.Room;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoomTemplateRepository implements RoomRepository{

    private final JdbcTemplate jdbcTemplate;

    public RoomTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Room> findByUserId(int AppUserId) {
        return null;
    }

    /**
     *
     * @param roomId
     * @return
     */
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
    public Room update(Room room) {
        return null;
    }

    @Override
    public boolean deleteByRoomId(int roomId) {
        return false;
    }
}
