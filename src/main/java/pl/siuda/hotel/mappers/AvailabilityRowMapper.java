package pl.siuda.hotel.mappers;

import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Availability;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AvailabilityRowMapper implements RowMapper<Availability> {

    @Override
    public Availability mapRow(ResultSet rs, int rowNum) throws SQLException {
        Availability availability = new Availability();
        availability.setHotelId(rs.getLong("hotel_id"));
        availability.setHotelName(rs.getString("hotel_name"));
        availability.setCity(rs.getString("city"));
        availability.setImage(rs.getString("image"));
        availability.setGrade(Grade.valueOf(rs.getString("grade")));
        availability.setRoomId(rs.getLong("room_id"));
        availability.setRoomGroupId(rs.getLong("room_group_id"));
        availability.setRoomType(RoomType.valueOf(rs.getString("room_type")));
        availability.setAvailabilityId(rs.getLong("reservation_id"));
        availability.setDescription(rs.getString("description"));
        if(rs.getTimestamp("start_date") != null)
        availability.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        if(rs.getTimestamp("end_date") != null)
        availability.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());

        return availability;
    }
}
