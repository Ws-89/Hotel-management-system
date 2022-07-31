package pl.siuda.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.dto.AvailabilityRequest;
import pl.siuda.hotel.dto.ReservationRequest;
import pl.siuda.hotel.models.Availability;
import pl.siuda.hotel.models.ReservationArrangement;
import pl.siuda.hotel.repositories.AvailabilityRepository;
import pl.siuda.hotel.repositories.ReservationRepository;
import pl.siuda.hotel.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.pricingAlgorithm.CalculatePriceAlgorithm;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService{

    @Autowired

    private final ReservationRepository reservationRepository;
    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm;
    private final CalculatePriceAlgorithm calculatePriceAlgorithm;
    private final CustomUserDetailsService customUserDetailsService;

    public ReservationService(ReservationRepository reservationRepository, AvailabilityRepository availabilityRepository, AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm, CalculatePriceAlgorithm calculatePriceAlgorithm, CustomUserDetailsService customUserDetailsService) {

        this.reservationRepository = reservationRepository;
        this.availabilityRepository = availabilityRepository;
        this.availabilityCheckProcessingAlgorithm = availabilityCheckProcessingAlgorithm;
        this.calculatePriceAlgorithm = calculatePriceAlgorithm;
        this.customUserDetailsService = customUserDetailsService;
    }

//    public List<Availability> getAvailableRooms(AvailabilityRequest request){
//        List<Availability> getPossibleAvailabilities = availabilityRepository.findRoomsByCity(request.getCity());
//        List<Long> takenRooms = filterTakenRooms(request, getPossibleAvailabilities);
//
//        return getOfferts(getPossibleAvailabilities, takenRooms);
//    }

    private <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public void userPlaceABooking(ReservationRequest request) {
        String userName = getUserName();
        ReservationArrangement reservation = new ReservationArrangement();
        reservation.setPartySize(request.getPartySize());
        reservation.setNumberOfRooms(request.getNumberOfRooms());
        reservation.setReservations(request.getReservations());
        reservation.setEmail(userName);
        reservation.setConfirmed(true);
        reservation.setPrice(request.getPrice());
        reservationRepository.save(reservation);
    }

    public void placeABooking(ReservationRequest request) {
        ReservationArrangement reservation = new ReservationArrangement();
        reservation.setPartySize(request.getPartySize());
        reservation.setNumberOfRooms(request.getNumberOfRooms());
        reservation.setReservations(request.getReservations());
        reservation.setEmail(request.getEmail());
        reservation.setConfirmed(true);
        reservation.setPrice(request.getPrice());
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

    private List<Availability> getOfferts(List<Availability> availabilitiesAndReservations, List<Long> takenRooms) {
        List<Availability> availabilities = availabilitiesAndReservations.stream()
                .filter(availability -> !takenRooms.contains(availability.getBookingDetails().getRoomId()))
                .filter(distinctByKey(p -> p.getBookingDetails().getRoomId()))
                .collect(Collectors.toList());

        availabilities.forEach(availability -> {
            availability.getBookingDetails().setPrice(calculatePriceAlgorithm.getPrice(availability.getBookingDetails()));
            availability.setAvailabilityId(0L);
        });
        return availabilities;
    }

    private List<Long> filterTakenRooms(AvailabilityRequest request, List<Availability> availabilitiesAndReservations) {
        return availabilitiesAndReservations.stream()
                .filter(availability -> availabilityCheckProcessingAlgorithm.isOverlapping(availability.getBookingDetails(), request))
                .map(item -> item.getBookingDetails().getRoomId())
                .collect(Collectors.toList());
    }
}

