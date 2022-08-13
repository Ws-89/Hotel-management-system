package pl.siuda.hotel.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.HotelDTO;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.mappers.HotelMapper;
import pl.siuda.hotel.models.*;
import pl.siuda.hotel.repositories.GuestRepository;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.repositories.ReservationRepository;
import pl.siuda.hotel.repositories.RoomRepository;
import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.requests.ReservationRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final HotelRepository hotelRepository;
    private AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;

    public BookingService(HotelRepository hotelRepository, AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm, RoomRepository roomRepository, ReservationRepository reservationRepository, GuestRepository guestRepository) {
        this.hotelRepository = hotelRepository;
        this.availabilityCheckProcessingAlgorithm = availabilityCheckProcessingAlgorithm;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }


    public void placeABooking(ReservationRequest request) {
        Room room = this.roomRepository.findById(request.getRoom().getRoomId()).orElseThrow(() -> new NotFoundException("Room not found"));

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setGuestName(request.getGuestName());
        reservation.setGuestLastName(request.getGuestLastName());
        reservation.setEmail(request.getEmail());
        reservation.setRequestMessage(request.getRequestMessage());
        reservation.setGuestAddress(request.getGuestAddress());
        reservation.setReservationStatus(ReservationStatus.Initialized);
        reservation.setPrice(room.getPrice());

        getPrincipal().ifPresent(theGuest -> theGuest.addReservation(reservation));

        room.addReservation(reservation);
        reservationRepository.save(reservation);
    }
    public Optional<Guest> getPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return guestRepository.getPrincipal(currentPrincipalName);
    }

    public AvailabilityResponse getAvailableRooms(AvailabilityRequest request){
        List<Hotel> listOfHotelsFromAGivenCity = hotelRepository.findByAddressCityAndEnabled(request.getCity(), true);

        List<HotelDTO> result = filterAvailableRooms(request, listOfHotelsFromAGivenCity)
                .stream()
                .map(h -> HotelMapper.INSTANCE.entityToDTO(h)).collect(Collectors.toList());

        return AvailabilityResponse.builder()
                .data(Map.of("availableHotels", result))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .numberOfRooms(request.getNumberOfRooms())
                .city(request.getCity())
                .partySize(request.getPartySize())
                .build();
    }

    private List<Hotel> filterAvailableRooms(AvailabilityRequest request, List<Hotel> hotels) {
        hotels.forEach(hotel -> hotel.getRooms()
                .stream()
                .filter(room -> room.getEnabled() == true &&
                        !room.getReservations().stream()
                                .anyMatch(reservation -> availabilityCheckProcessingAlgorithm.isOverlapping(reservation, request))));

        hotels.removeIf(hotel -> hotel.getRooms().size() == 0);
        return hotels;
    }


}
