package pl.siuda.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.siuda.hotel.dao.HotelRepo;
import pl.siuda.hotel.dao.RoomRepo;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepo;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.hotel.HotelService;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.reservation.ReservationRepository;
import pl.siuda.hotel.room.Room;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.room.RoomService;

import java.time.LocalDateTime;
import java.util.Date;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HotelApplicationTests {

	@Autowired
	HotelRepo hotelRepository;

	@Autowired
    HotelService hotelService;

	@Autowired
	RoomRepo roomRepo;

	@Autowired
	RoomService roomService;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	GuestRepo guestRepo;

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
		h1.addRoom(r1);
		h1.addRoom(r2);
		System.out.println(hotelRepository.save(h1));

	}

	@Test
	void updateTest(){
		Hotel h1 = hotelRepository.findById(1L).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", 2L)));
		h1.setPhoneNumber("123");
		h1.setEmail("asdf@gmail.com");
		System.out.println(hotelRepository.save(h1));
	}

	@Test
	void addRoomAtSpecifiedHotel(){
		Room room = new Room();
		room.setNumber(201);
		room.setRoomType(RoomType.SINGLE);
		roomService.createRoomAtSpecifiedHotel(1L, room);
	}

	@Test
	void addReservationToRoom(){
		Guest guest = new Guest();

		Reservation reservation = new Reservation();
		reservation.setFrom_data(LocalDateTime.of(2021, 05, 15, 02,01,1));
		reservation.setTo_data(LocalDateTime.of(2021, 05, 15, 02,01,2));
		guest.addReservation(reservation);
		guestRepo.save(guest);

		Room room = new Room();
		room.setNumber(202);
		room.setRoomType(RoomType.DOUBLE);
		room.addReservation(reservation);
		roomService.createRoomAtSpecifiedHotel(1L, room);

		for (Room r: roomRepo.findAll()){
			System.out.println(reservationRepository.findAllById(r.getReservationIds()));
		}
	}

	@Test
	void checkTime(){
		System.out.println(LocalDateTime.now());
	}


}
