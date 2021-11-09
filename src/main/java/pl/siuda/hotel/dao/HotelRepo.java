package pl.siuda.hotel.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.hotel.Hotel;

import java.util.Optional;

@Repository
public interface HotelRepo extends CrudRepository<Hotel, Long> {

    public Optional<Hotel> findById(Long id);

    public Optional<Hotel> findByName(String name);


}
