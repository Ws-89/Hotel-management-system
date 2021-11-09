package pl.siuda.hotel.hotel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.siuda.hotel.dao.DAO;
import pl.siuda.hotel.mappers.HotelRowMapper;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Repository
public class HotelRepository implements DAO<Hotel> {

    private final JdbcTemplate jdbcTemplate;

    public HotelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Hotel> findAll() {
        var sql = """
                SELECT * 
                FROM hotel
                LIMIT 100
                """;
        List<Hotel> hotels = jdbcTemplate.query(sql, new HotelRowMapper());

        return hotels;
    }

    @Override
    public int create(Hotel hotel) {
        var sql = """
                INSERT INTO hotel 
                (name, street, city, state, country, zipcode, phone_number, email, grade)
                VALUES (?,?,?,?,?,?,?,?,?);
                """;
        return jdbcTemplate.update(sql, hotel.getName(), hotel.getStreet(), hotel.getCity(),
                hotel.getState(), hotel.getCountry(), hotel.getZipcode(), hotel.getPhoneNumber(), hotel.getEmail(), hotel.getGrade().name());
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        var sql = """
                SELECT * 
                FROM hotel 
                WHERE id = ?
                """;
        List<Hotel> hotel = jdbcTemplate.query(sql, new HotelRowMapper(), id);
        return hotel.stream().findFirst();
    }

    public Optional<Hotel> findByName(String name) {
        var sql = """
                SELECT * 
                FROM hotel 
                WHERE name = ?
                """;
        List<Hotel> hotel = jdbcTemplate.query(sql, new HotelRowMapper(), name);
        return hotel.stream().findFirst();
    }

    @Override
    public int update(Hotel hotel, Long id) {
        var sql = """
                UPDATE hotel 
                SET name = ?, street = ?, city = ?, state = ?, country = ?, zipcode = ?, phone_number =?, email = ?, grade = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, hotel.getName(), hotel.getStreet(), hotel.getCity(),
                hotel.getState(), hotel.getCountry(), hotel.getZipcode(), hotel.getPhoneNumber(), hotel.getEmail(), hotel.getGrade().name(), id);
    }

    @Override
    public int delete(Long id) {
        var sql = """
                DELETE FROM hotel WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);
    }

    public void addRoom(Room room, Long hotelId){

    }

    public void deleteRoom(Long hotelId, Long roomId){

    }

    public void updateRoom(Room room, Long roomId, Long hotelId){

    }

    public void setOpeningTimes(OpeningTimes openingTimes, Long id){

    }

    public void removeOpeningTimes(Long id){

    }

    public void editOpeningTimes(Long id){

    }
}
