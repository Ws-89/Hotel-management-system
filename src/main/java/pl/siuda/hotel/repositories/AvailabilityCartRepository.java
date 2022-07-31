package pl.siuda.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.models.AvailabilityCart;


@Repository
public interface AvailabilityCartRepository extends JpaRepository<AvailabilityCart, Long> {



}
