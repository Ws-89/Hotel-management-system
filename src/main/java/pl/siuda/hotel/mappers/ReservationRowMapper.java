package pl.siuda.hotel.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.siuda.hotel.reservation.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationNo(rs.getLong("RESERVATION_NO"));

        return reservation;
    }
}
