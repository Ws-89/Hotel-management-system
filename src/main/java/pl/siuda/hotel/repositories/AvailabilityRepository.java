package pl.siuda.hotel.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.mappers.AvailabilityRowMapper;
import pl.siuda.hotel.models.Availability;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

//    @Query(rowMapperClass = AvailabilityRowMapper.class, value = "SELECT hotel.hotel_id, hotel.name AS hotel_name, hotel.city, hotel.grade, \n" +
//            "hotel.image, room.room_id, room_group.room_group_id, room_group.description, \n " +
//            "room_group.room_type, reservation.reservation_id, \n" +
//            "reservation.start_date, reservation.end_date \n" +
//            "FROM hotel INNER JOIN room_group ON room_group.hotel_id = hotel.hotel_id \n" +
//            "INNER JOIN room ON room.room_group_id = room_group.room_group_id\n" +
//            "LEFT JOIN reservation ON reservation.room_id = room.room_id\n" +
//            "WHERE hotel.city = :city")
//    List<Availability> findRoomsByCity(@Param("city") String city);
}
