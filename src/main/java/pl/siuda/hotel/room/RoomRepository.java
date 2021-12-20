package pl.siuda.hotel.room;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    public Optional<Room> findByNumber(Integer id);

    @Query("SELECT * FROM room WHERE hotel_id = :id")
    public List<Room> findByHotelId(Long id);

    @Query("SELECT * FROM room WHERE number = :room_number AND hotel_id = :hotel_id")
    public Optional<Room> findByRoomNumberAndHotelId(Integer room_number, Long hotel_id);


}
