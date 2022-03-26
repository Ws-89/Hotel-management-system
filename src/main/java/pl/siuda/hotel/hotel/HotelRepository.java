package pl.siuda.hotel.hotel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    Optional<Hotel> findByName(String name);
}
