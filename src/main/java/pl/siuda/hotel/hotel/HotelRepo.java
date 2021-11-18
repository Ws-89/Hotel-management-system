package pl.siuda.hotel.hotel;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.mappers.HotelRowMapper;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepo extends CrudRepository<Hotel, Long> {

    @Override
    public List<Hotel> findAll();

    public Optional<Hotel> findById(Long id);

    public Optional<Hotel> findByName(String name);

}
