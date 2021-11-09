package pl.siuda.hotel.room;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.dao.DAO;
import pl.siuda.hotel.mappers.RoomRowMapper;
import pl.siuda.hotel.mappers.RoomWithReservationsRowMapper;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepository implements DAO<Room> {


    private final JdbcTemplate jdbcTemplate;

    public RoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Room> findAll() {
        var sql = """
                SELECT *
                FROM room
                """;
        List<Room> list = jdbcTemplate.query(sql, new RoomRowMapper());
        return list;
    }

    public List<Room> findAllRoomsByHotelId(Long id){
        var sql = """
                SELECT *
                FROM room
                WHERE hotel_id = ?
                """;
        List<Room> list = jdbcTemplate.query(sql, new RoomRowMapper());
        return list;
    }

    public List<Room> findAllWithReservation() {
        var sql = """
                SELECT id, number, room_type, reservation_no FROM room LEFT JOIN reservation ON room.id = reservation.room_id
                """;
        List<Room> list = jdbcTemplate.query(sql, new RoomWithReservationsRowMapper());
        return list;
    }

    @Override
    public int create(Room room) {
        var sql = """
                INSERT INTO room (number, room_type) 
                VALUES (?,?);
                """;
        return jdbcTemplate.update(sql, room.getNumber(), room.getRoomType().name());
    }

    @Override
    public Optional<Room> findById(Long id) {
        var sql = """
                SELECT * FROM room WHERE id = ? 
                """;
        List<Room> list = jdbcTemplate.query(sql , new RoomRowMapper(), id);
        return list.stream().findFirst();
    }

    @Override
    public int update(Room room, Long id) {
        var sql = """
                UPDATE room SET number = ?, room_type = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, room.getNumber(), room.getRoomType().name(), id);
    }

    @Override
    public int delete(Long id) {
        var sql = """
                DELETE FROM room WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    public Optional<Room> findByNumber(Integer number){
        var sql = """
                SELECT * FROM room WHERE number = ?
                """;
        List<Room> list = jdbcTemplate.query(sql, new RoomRowMapper(), number);
        return list.stream().findFirst();
    }
}
