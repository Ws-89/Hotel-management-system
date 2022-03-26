package pl.siuda.hotel.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.reservation.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.reservation.pricingAlgorithm.CalculatePriceAlgorithm;
import pl.siuda.hotel.room.RoomService;
import pl.siuda.hotel.security.CustomUserDetails;
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
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;
    private final AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm;
    private final CalculatePriceAlgorithm calculatePriceAlgorithm;
    private final CustomUserDetailsService customUserDetailsService;

    public ReservationService(RoomService roomService, ReservationRepository reservationRepository, AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm, CalculatePriceAlgorithm calculatePriceAlgorithm, CustomUserDetailsService customUserDetailsService) {
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
        this.availabilityCheckProcessingAlgorithm = availabilityCheckProcessingAlgorithm;
        this.calculatePriceAlgorithm = calculatePriceAlgorithm;
        this.customUserDetailsService = customUserDetailsService;
    }

    public List<Availability> getAvailability(AvailabilityRequest request){
        List<Availability> getPossibleAvailabilities = reservationRepository.findRoomsByCity(request.getCity());
        List<Long> takenRooms = filterTakenRooms(request, getPossibleAvailabilities);

        return getOfferts(getPossibleAvailabilities, takenRooms);
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public void makeAReservation(ReservationRequest request) {
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


    public boolean cancelReservation(Long reservation_id) {
        Optional.ofNullable(reservationRepository.findById(reservation_id)).orElseThrow(()-> new NotFoundException(""));
        return true;
    }

    private List<Availability> getOfferts(List<Availability> availabilitiesAndReservations, List<Long> takenRooms) {
        List<Availability> availabilities = availabilitiesAndReservations.stream()
                .filter(availability -> !takenRooms.contains(availability.getRoom_id()))
                .filter(distinctByKey(p -> p.getRoom_id()))
                .collect(Collectors.toList());

        availabilities.forEach(availability -> availability.setPrice(calculatePriceAlgorithm.getPrice(availability)));
        return availabilities;
    }

    private List<Long> filterTakenRooms(AvailabilityRequest request, List<Availability> availabilitiesAndReservations) {
        return availabilitiesAndReservations.stream()
                .filter(availability -> availabilityCheckProcessingAlgorithm.isOverlapping(availability, request))
                .map(item -> item.getRoom_id())
                .collect(Collectors.toList());
    }
}

