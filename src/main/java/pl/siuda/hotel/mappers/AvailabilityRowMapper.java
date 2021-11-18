package pl.siuda.hotel.mappers;

import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Availability;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvailabilityRowMapper implements RowMapper<Availability> {

    @Override
    public Availability mapRow(ResultSet rs, int rowNum) throws SQLException {
        Availability availability = new Availability();
        availability.setHotel_id(rs.getLong("hotel_id"));
        availability.setHotel_name(rs.getString("hotel_name"));
        availability.setCity(rs.getString("city"));
        availability.setGrade(Grade.valueOf(rs.getString("grade")));
        availability.setRoom_id(rs.getLong("room_id"));
        availability.setRoomType(RoomType.valueOf(rs.getString("room_type")));
        availability.setReservation_id(rs.getLong("reservation_id"));
        if(rs.getTimestamp("from_data") != null)
        availability.setFrom_date(rs.getTimestamp("from_data").toLocalDateTime());
        if(rs.getTimestamp("to_data") != null)
        availability.setTo_date(rs.getTimestamp("to_data").toLocalDateTime());

        return availability;
    }
}
