package pl.siuda.hotel.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends CrudRepository<Room, Long> {

    public Optional<Room> findByNumber(Integer id);

    @Query("SELECT * FROM room WHERE hotel_id = :id")
    public List<Room> findByHotelId(Long id);



}
