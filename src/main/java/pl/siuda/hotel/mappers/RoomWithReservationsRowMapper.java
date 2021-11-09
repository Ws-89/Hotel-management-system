package pl.siuda.hotel.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.siuda.hotel.room.Room;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomWithReservationsRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setNumber(rs.getInt("number"));
        room.setRoomType(RoomType.valueOf(rs.getString("room_type")));
        if(rs.getLong("reservation_no") != 0){
            Reservation reservation = new Reservation();
            reservation.setReservationNo(rs.getLong("reservation_no"));
            room.getReservations().add(reservation);
        }
        return room;
    }
}
