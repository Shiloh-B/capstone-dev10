package capstone.data;

import capstone.data.mappers.RoomMapper;
import capstone.models.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class RoomTemplateRepository implements RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoomTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Room> findByUserId(int appUserId) {
        final String sql = "select r.room_id, r.`name` from room r " +
                "inner join room_has_user rhu on rhu.room_id = r.room_id " +
                "where rhu.user_id = ?;";
        return jdbcTemplate.query(sql, new RoomMapper(), appUserId);
    }

    @Override
    public Room findByRoomId(int roomId) {
        final String sql = "select * from room where room_id = ?";
        Room room = jdbcTemplate.query(sql, new RoomMapper(), roomId).stream().findFirst().orElse(null);
        return room;
    }

    @Override
    public Room add(Room room) {
        final String sql = "insert into room (`name`) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, room.getRoomName());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        room.setRoomId(keyHolder.getKey().intValue());
        return room;
    }

    @Override
    public boolean update(Room room) {
        final String sql = "update room set "
                + "`name` = ?, "
                + "where room_id = ?";
        return jdbcTemplate.update(sql,
                room.getRoomName(), room.getRoomId()) > 0;
    }

    @Override
    public boolean deleteByRoomId(int roomId) {
        return jdbcTemplate.update("delete from room where room_id = ?;", roomId) > 0;
    }
}
