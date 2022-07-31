package pl.siuda.hotel.mappers;

import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.models.enums.RoomType;
import pl.siuda.hotel.models.Availability;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AvailabilityRowMapper implements RowMapper<Availability> {

    @Override
    public Availability mapRow(ResultSet rs, int rowNum) throws SQLException {
        Availability availability = new Availability();
        availability.getBookingDetails().setHotelId(rs.getLong("hotel_id"));
        availability.getBookingDetails().setHotelName(rs.getString("hotel_name"));
        availability.getBookingDetails().setCity(rs.getString("city"));
        availability.getBookingDetails().setImage(rs.getString("image"));
        availability.getBookingDetails().setGrade(Grade.valueOf(rs.getString("grade")));
        availability.getBookingDetails().setRoomId(rs.getLong("room_id"));
        availability.setRoomGroupId(rs.getLong("room_group_id"));
        availability.getBookingDetails().setRoomType(RoomType.valueOf(rs.getString("room_type")));
        availability.setAvailabilityId(rs.getLong("reservation_id"));
        availability.setDescription(rs.getString("description"));
        if(rs.getTimestamp("start_date") != null)
        availability.getBookingDetails().setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        if(rs.getTimestamp("end_date") != null)
        availability.getBookingDetails().setEndDate(rs.getTimestamp("end_date").toLocalDateTime());

        return availability;
    }
}
