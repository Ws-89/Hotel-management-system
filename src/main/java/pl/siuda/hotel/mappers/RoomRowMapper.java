//package pl.siuda.hotel.mappers;
//
//import org.springframework.jdbc.core.RowMapper;
//import pl.siuda.hotel.room.Room;
//import pl.siuda.hotel.enums.RoomType;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//
//public class RoomRowMapper implements RowMapper<Room> {
//    @Override
//    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
//        final Room room = new Room();
//        room.setId(rs.getLong("id"));
//        room.setNumber(rs.getInt("number"));
//        room.setRoomType(RoomType.valueOf(rs.getString("room_type")));
//        room.setReservations(null);
//        return room;
//    }
//}
