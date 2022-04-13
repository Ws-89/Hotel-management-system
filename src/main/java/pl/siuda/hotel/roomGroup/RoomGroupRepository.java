package pl.siuda.hotel.roomGroup;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomGroupRepository extends CrudRepository<RoomGroup, Long> {

    @Query("SELECT * FROM room_group WHERE hotel_id = :id")
    public List<RoomGroup> findRoomGroupsByHotelId(@Param("id")Long id);
}
