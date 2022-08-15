package pl.siuda.hotel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.ReservationStatus;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {

    Guest findByEmail(String email);

    @EntityGraph(value = "graph.guestProfile")
    @Query("SELECT g FROM Guest g WHERE g.email = ?1")
    Optional<Guest> getPrincipal(String email);

    @EntityGraph(value = "graph.guestProfile")
    Optional<Guest> findById(UUID id);

    @EntityGraph(value = "graph.guestProfile")
    Page<Guest> findAll(Pageable page);

    @EntityGraph(value = "graph.userReservationsRooms")
    @Query("SELECT g FROM Guest g JOIN g.reservations gr JOIN gr.room grr JOIN grr.hotel grrh WHERE grrh.hotelId = ?1 AND gr.reservationStatus = ?2")
    Page<Guest> getCurrentHotelGuests(UUID hotelId, ReservationStatus status, Pageable page);

    Page<Guest> findAllByFirstNameContainingAndLastNameContainingAndReservations_ReservationStatusAndReservations_Room_Hotel_HotelId(String firstName, String lastName, ReservationStatus status, Long hotelId, Pageable page);
}
