package pl.siuda.hotel.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
