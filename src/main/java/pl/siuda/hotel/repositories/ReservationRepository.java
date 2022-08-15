package pl.siuda.hotel.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.ReservationStatus;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @EntityGraph(value = "graph.reservationWithGuest")
    Page<Reservation> findByReservationStatusAndGuest_GuestId(ReservationStatus status, UUID id, Pageable page);

    @EntityGraph(value = "graph.reservationWithGuest")
    Page<Reservation> findByReservationStatusAndGuest_Email(ReservationStatus status, String email, Pageable page);

    @EntityGraph(value = "graph.reservationWithRoom")
    Page<Reservation> findByReservationStatusAndRoom_Hotel_HotelId(ReservationStatus status, UUID id, Pageable page);

    @EntityGraph(value = "graph.reservationWithGuest")
    Page<Reservation> findByReservationStatusAndRoom_RoomId(ReservationStatus status, UUID id, Pageable page);

    @EntityGraph(value = "graph.reservationWithRoom")
    Optional<Reservation> findById(UUID id);

    Page<Reservation> findByReservationStatusAndRoom_Hotel_HotelIdAndGuestLastNameContaining(
            ReservationStatus status, UUID hotelId, String lastName, Pageable page);

//    Page<Reservation> findByRoom_Hotel_HotelId(ReservationStatus status, Long hotelId, Pageable page);
}

