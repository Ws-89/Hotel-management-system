package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AvailabilityCartRepository extends CrudRepository<AvailabilityCart, Long> {

    Optional<AvailabilityCart> findByGuestId(Long guest_id);
}
