package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.data.repository.CrudRepository;
import pl.siuda.hotel.reservation.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

}
