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

    /**
     * Finds all Rooms a user is currently in.
     *
     * @param appUserId ID of an AppUser to get Rooms for.
     * @return List of Rooms the AppUser with ID appUserId is in.
     */
    @Override
    public List<Room> findByUserId(int appUserId) {
        final String sql = "select r.room_id, r.`name` from room r " +
                "inner join room_has_user rhu on rhu.room_id = r.room_id " +
                "where rhu.user_id = ?;";
        return jdbcTemplate.query(sql, new RoomMapper(), appUserId);
    }

    /**
     * Returns a room given its RoomId
     *
     * @param roomId ID of Room to get from database.
     * @return Room object found
     */
    @Override
    public Room findByRoomId(int roomId) {
        final String sql = "select * from room where room_id = ?";
        Room room = jdbcTemplate.query(sql, new RoomMapper(), roomId).stream().findFirst().orElse(null);
        return room;
    }

    /**
     * Adds a Room to database.
     *
     * @param room Room to add to database.
     * @return Room added.
     */
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

    /**
     * Update a Room in database. (i.e., rename it.)
     *
     * @param room Room to update
     * @return true if updated false if not.
     */
    @Override
    public boolean update(Room room) {
        final String sql = "update room set "
                + "`name` = ? "
                + "where room_id = ?;";
        return jdbcTemplate.update(sql,
                room.getRoomName(), room.getRoomId()) > 0;
    }

    /**
     * Delete a Room from database given its Id.
     *
     * @param roomId Id to delete room by.
     * @return true if deleted.
     */
    @Override
    public boolean deleteByRoomId(int roomId) {
        return jdbcTemplate.update("delete from room where room_id = ?;", roomId) > 0;
    }
}
