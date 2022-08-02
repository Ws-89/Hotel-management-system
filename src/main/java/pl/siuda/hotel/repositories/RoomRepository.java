package pl.siuda.hotel.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

//    @Query("SELECT * FROM room_group WHERE hotel_id = :id")
//    public List<RoomGroup> findRoomGroupsByHotelId(@Param("id")Long id);

    public List<Room> findByHotelHotelId(Long id);
}
