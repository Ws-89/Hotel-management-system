package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AvailabilityCartRepository extends CrudRepository<AvailabilityCart, Long> {



}
