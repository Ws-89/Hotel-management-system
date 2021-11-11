package pl.siuda.hotel.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepo extends CrudRepository<Hotel, Long> {

    public Optional<Hotel> findById(Long id);

    public Optional<Hotel> findByName(String name);

}
