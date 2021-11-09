package pl.siuda.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.hotel.HotelRepository;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.room.Room;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.room.RoomService;

import java.util.Optional;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HotelApplicationTests {

	@Autowired
	HotelRepository hotelRepository;

	@Test
	void contextLoads() {
		Room r1 = new Room();
		r1.setNumber(130);
		r1.setRoomType(RoomType.DOUBLE);
		Room r2 = new Room();
		r2.setNumber(131);
		r2.setRoomType(RoomType.DOUBLE);
		Hotel h1 = new Hotel();
		h1.setName("Flex hotel");
		Address a1 = new Address();
		a1.setStreet("Elisabethhoff 50");
		a1.setCity("Leiden");
		a1.setState("Nord-Holland");
		a1.setCountry("Netherlands");
		a1.setZipcode("12-345");
		h1.setAddress(a1);
		Contact c1 = new Contact();
		c1.setEmail("wiktorsiuda@gmail.com");
		c1.setPhoneNumber("999999999");
		h1.setContact(c1);
		h1.setGrade(Grade.TWOSTARS);
		h1.addRooms(r1);
		h1.addRooms(r2);
		System.out.println(hotelRepository.create(h1));

	}

	@Test
	void updateTest(){
		Hotel h1 = hotelRepository.findById(2L).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", 2L)));
		h1.setPhoneNumber("123");
		h1.setEmail("asdf@gmail.com");
		System.out.println(hotelRepository.update(h1, 2L));
	}



}
