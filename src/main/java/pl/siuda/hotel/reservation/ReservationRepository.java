package pl.siuda.hotel.reservation;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.mappers.AvailabilityRowMapper;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationArrangement, Long> {

    @Query(rowMapperClass = AvailabilityRowMapper.class, value = "SELECT hotel.hotel_id, hotel.name AS hotel_name, hotel.city, hotel.grade, hotel.image, room.room_id, room.room_type, reservation.reservation_id, \n" +
            "reservation.from_date, reservation.to_date \n" +
            "FROM hotel INNER JOIN room ON room.hotel_id = hotel.hotel_id\n" +
            "LEFT JOIN reservation ON reservation.room_id = room.room_id\n" +
            "WHERE hotel.city = :city")
    List<Availability> findRoomsByCity(String city);

}
