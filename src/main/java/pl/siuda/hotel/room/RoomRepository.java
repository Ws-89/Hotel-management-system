package pl.siuda.hotel.room;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("SELECT * FROM room WHERE hotel_id = :id")
    public List<Room> findByHotelId(@Param("id") Long id);

    @Query("SELECT * FROM room WHERE number = :room_number AND hotel_id = :hotel_id")
    public Optional<Room> findByRoomNumberAndHotelId(@Param("room_number") Integer room_number,@Param("id") Long hotel_id);

    @Query("SELECT * FROM room INNER JOIN room_group " +
            "ON room.room_group_id = room_group.room_group_id " +
            "INNER JOIN hotel ON room_group.hotel_id = hotel.hotel_id " +
            "WHERE hotel.hotel_id = :hotel_id")
    public List<Room> getRoomListByHotelId(@Param("hotel_id") Long id);
}
