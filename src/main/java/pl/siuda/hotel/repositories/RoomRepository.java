package pl.siuda.hotel.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

//    @Query("SELECT * FROM room_group WHERE hotel_id = :id")
//    public List<RoomGroup> findRoomGroupsByHotelId(@Param("id")Long id);
    @EntityGraph(value = "graph.roomWReservationsWoGuest")
    public Optional<Room> findById(UUID id);
    public Page<Room> findByHotelHotelId(UUID id, Pageable page);
}
