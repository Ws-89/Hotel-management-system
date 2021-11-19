package pl.siuda.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.siuda.hotel.guest.GuestService;
import pl.siuda.hotel.hotel.HotelRepo;
import pl.siuda.hotel.reservation.AvailabilityRequest;
import pl.siuda.hotel.reservation.ReservationService;
import pl.siuda.hotel.room.RoomRepo;
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

	@Autowired
	GuestService guestService;

	@Autowired
	ReservationService reservationService;

	@Test
	void contextLoads() {
		Room r1 = new Room();
		r1.setNumber(11);
		r1.setRoomType(RoomType.DOUBLE);
		Room r2 = new Room();
		r2.setNumber(12);
		r2.setRoomType(RoomType.DOUBLE);
		Hotel h1 = new Hotel();
		h1.setName("Novotel");
		Address a1 = new Address();
		a1.setStreet("Marszałkowska");
		a1.setCity("Warszawa");
		a1.setState("Mazowieckie");
		a1.setCountry("Polska");
		a1.setZipcode("123-45");
		h1.setAddress(a1);
		Contact c1 = new Contact();
		c1.setEmail("wiktorsiuda@gmail.com");
		c1.setPhoneNumber("999999999");
		h1.setContact(c1);
		h1.setGrade(Grade.FOURSTARS);
		h1.addRoom(r1);
		h1.addRoom(r2);
		System.out.println(hotelRepository.save(h1));

	}

	@Test
	void updateTest(){
		Hotel h1 = hotelRepository.findById(1L).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", 1L)));
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
	void addReservationToExistingGuest(){
		Guest guest = guestService.findByEmail("wiktorsiuda3@gmail.com");

		Room room = roomService.findByRoomNumberAndHotelId(12, 3L);
		Room room2 = roomService.findByRoomNumberAndHotelId(11, 3L);

		Reservation reservation2 = new Reservation();
		reservation2.setFrom_data(LocalDateTime.of(2021, 1, 10, 02,01,1));
		reservation2.setTo_data(LocalDateTime.of(2021, 1, 15, 02,01,2));

		reservation2.addRoom(room);
		reservation2.addRoom(room2);
		guest.addReservation(reservation2);
		guestRepo.save(guest);


	}

	@Test
	void checkReservation(){
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setFrom(LocalDateTime.of(2021, 5, 13, 12, 00, 00));
		availabilityRequest.setTo(LocalDateTime.of(2021, 5, 21, 00, 00, 00));
		availabilityRequest.setCity("Leiden");

		System.out.println(reservationService.getAvailability(availabilityRequest));

	}


}
