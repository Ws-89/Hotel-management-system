package pl.siuda.hotel.reservation;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.mappers.AvailabilityRowMapper;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationArrangement, Long> {

//    @Query(rowMapperClass = AvailabilityRowMapper.class, value = "SELECT hotel.hotel_id, hotel.name AS hotel_name, hotel.city, hotel.grade, hotel.image, room.room_id, room.room_type, reservation.reservation_id, \n" +
//            "reservation.from_date, reservation.to_date \n" +
//            "FROM hotel INNER JOIN room ON room.hotel_id = hotel.hotel_id\n" +
//            "LEFT JOIN reservation ON reservation.room_id = room.room_id\n" +
//            "WHERE hotel.city = :city")
//    List<Availability> findRoomsByCity(@Param("city") String city);


    @Query(rowMapperClass = AvailabilityRowMapper.class, value = "SELECT hotel.hotel_id, hotel.name AS hotel_name, hotel.city, hotel.grade, \n" +
            "hotel.image, room.room_id, room_group.room_group_id, room_group.description, \n " +
            "room_group.room_type, reservation.reservation_id, \n" +
            "reservation.from_date, reservation.to_date \n" +
            "FROM hotel INNER JOIN room_group ON room_group.hotel_id = hotel.hotel_id \n" +
            "INNER JOIN room ON room.room_group_id = room_group.room_group_id\n" +
            "LEFT JOIN reservation ON reservation.room_id = room.room_id\n" +
            "WHERE hotel.city = :city")
    List<Availability> findRoomsByCity(@Param("city") String city);
}
