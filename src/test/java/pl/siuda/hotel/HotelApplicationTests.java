package pl.siuda.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.guest.GuestService;
import pl.siuda.hotel.hotel.HotelRepository;
import pl.siuda.hotel.reservation.AvailabilityRequest;
import pl.siuda.hotel.reservation.ReservationService;
import pl.siuda.hotel.room.RoomRepository;
import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.embeddedClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepository;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.hotel.HotelService;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.reservation.ReservationRepository;
import pl.siuda.hotel.room.Room;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.room.RoomRequest;
import pl.siuda.hotel.room.RoomService;

import java.time.LocalDateTime;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HotelApplicationTests {

	@Autowired
    HotelRepository hotelRepository;

	@Autowired
    HotelService hotelService;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomService roomService;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
    GuestRepository guestRepository;

	@Autowired
	GuestService guestService;

	@Autowired
    ReservationService reservationService;

	@Autowired
	AdminRepository adminRepository;

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
		RoomRequest roomRequest = new RoomRequest();
		roomRequest.setNumber(201);
		roomRequest.setRoomType(RoomType.SINGLE);
		roomService.createRoomAtSpecifiedHotel(1L, roomRequest);
	}


	@Test
	void addReservationToExistingGuest(){
		Guest guest = guestService.findByEmail("wiktorsiuda5@gmail.com");

		Room room = roomService.findByRoomNumberAndHotelId(131, 1L);
		Room room2 = roomService.findByRoomNumberAndHotelId(130, 1L);

		Reservation reservation2 = new Reservation();
		reservation2.setFrom_data(LocalDateTime.of(2022, 1, 1, 00,00,1));
		reservation2.setTo_data(LocalDateTime.of(2022, 1, 5, 00,00,1));

		reservation2.addRoom(room);
		reservation2.addRoom(room2);
		guest.addReservation(reservation2);
		guestRepository.save(guest);


	}

	@Test
	void checkReservation(){
		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setFrom_date(LocalDateTime.of(2021, 5, 13, 12, 00, 00));
		availabilityRequest.setTo_date(LocalDateTime.of(2021, 5, 21, 00, 00, 00));
		availabilityRequest.setCity("Leiden");

		System.out.println(reservationService.getAvailability(availabilityRequest));

	}



}
