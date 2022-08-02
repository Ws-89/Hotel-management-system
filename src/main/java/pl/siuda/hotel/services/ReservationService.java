package pl.siuda.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.models.AvailabilityResponse;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.repositories.ReservationRepository;
import pl.siuda.hotel.repositories.RoomRepository;
import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.requests.ReservationRequest;
import pl.siuda.hotel.security.CustomUserDetailsService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@Transactional
public class ReservationService{

    @Autowired

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm;
//    private final CalculatePriceAlgorithm calculatePriceAlgorithm;
    private final CustomUserDetailsService customUserDetailsService;

    public ReservationService(
            ReservationRepository reservationRepository,
            HotelRepository hotelRepository, RoomRepository roomRepository, AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm,
//            CalculatePriceAlgorithm calculatePriceAlgorithm,
            CustomUserDetailsService customUserDetailsService) {

        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.availabilityCheckProcessingAlgorithm = availabilityCheckProcessingAlgorithm;
//        this.calculatePriceAlgorithm = calculatePriceAlgorithm;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AvailabilityResponse getAvailableRooms(AvailabilityRequest request){
         List<Hotel> listOfHotelsFromAGivenCity = hotelRepository.findByAddressCity(request.getCity());

         List<Hotel> result = filterAvailableRooms(request, listOfHotelsFromAGivenCity);

        return AvailabilityResponse.builder()
                .data(Map.of("availableHotels", result))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .numberOfRooms(request.getNumberOfRooms())
                .city(request.getCity())
                .partySize(request.getPartySize())
                .build();
    }

//    public void userPlaceABooking(ReservationRequest request) {
//        String userName = getUserName();
//        ReservationArrangement reservation = new ReservationArrangement();
//        reservation.setPartySize(request.getPartySize());
//        reservation.setNumberOfRooms(request.getNumberOfRooms());
//        reservation.setReservations(request.getReservations());
//        reservation.setEmail(userName);
//        reservation.setConfirmed(true);
//        reservation.setPrice(request.getPrice());
//        reservationRepository.save(reservation);
//    }
//
    public void placeABooking(ReservationRequest request) {
        Room room = this.roomRepository.findById(request.getRoom().getRoomId()).orElseThrow(() -> new NotFoundException("Room not found"));

        Reservation reservation = new Reservation();
        reservation.setRoom(request.getRoom());
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        room.addReservation(reservation);
        reservationRepository.save(reservation);
    }

    private String getUserName() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if(authentication instanceof UserDetails){
            userName = ((UserDetails) authentication).getUsername();
        }else {
            throw new IllegalArgumentException();
        }
        return userName;
    }

//    private List<Availability> getOfferts(List<Availability> availabilitiesAndReservations, List<Long> takenRooms) {
//        List<Availability> availabilities = availabilitiesAndReservations.stream()
//                .filter(availability -> !takenRooms.contains(availability.getBookingDetails().getRoomId()))
//                .filter(distinctByKey(p -> p.getBookingDetails().getRoomId()))
//                .collect(Collectors.toList());
//
//        availabilities.forEach(availability -> {
//            availability.getBookingDetails().setPrice(calculatePriceAlgorithm.getPrice(availability.getBookingDetails()));
//            availability.setAvailabilityId(0L);
//        });
//        return availabilities;
//    }
//



    private List<Hotel> filterAvailableRooms(AvailabilityRequest request, List<Hotel> hotels) {
       hotels.forEach(hotel -> hotel.getRooms().removeIf(room -> room.getReservations().stream()
               .anyMatch(reservation -> availabilityCheckProcessingAlgorithm.isOverlapping(reservation, request))));
        return hotels;
    }
}

