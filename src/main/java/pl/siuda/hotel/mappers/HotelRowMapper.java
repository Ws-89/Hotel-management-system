package pl.siuda.hotel.mappers;

import org.springframework.jdbc.core.RowMapper;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.hotel.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotel_id(rs.getLong("id"));
        hotel.setName(rs.getString("name"));
        Address address = new Address();
        address.setStreet(rs.getString("street"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setCountry(rs.getString("country"));
        address.setZipcode(rs.getString("zipcode"));
        hotel.setAddress(address);
        Contact contact = new Contact();
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setEmail(rs.getString("email"));
        hotel.setContact(contact);
        hotel.setGrade(Grade.valueOf(rs.getString("grade")));
        return hotel;
    }
}
