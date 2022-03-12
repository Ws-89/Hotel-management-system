package pl.siuda.hotel.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.reservation.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.reservation.pricingAlgorithm.CalculatePriceAlgorithm;
import pl.siuda.hotel.room.RoomService;
import pl.siuda.hotel.security.CustomUserDetails;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservation {

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

    public Set<Availability> getRoomsByCity(String city) {
        return reservationRepository.findRoomsByCity(city);
    }

    @Override
    public Set<Offert> getAvailability(AvailabilityRequest request){
        Set<Availability> availabilitiesAndReservations = reservationRepository.findRoomsByCity(request.getCity());
        Set<Long> takenRooms = getTakenRooms(request, availabilitiesAndReservations);

        return getOfferts(availabilitiesAndReservations, takenRooms);
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public Reservation makeAReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        request.requestToEntity(reservation);
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if(authentication instanceof UserDetails){
            userName = ((UserDetails) authentication).getUsername();
        }else {
            throw new IllegalArgumentException();
        }
        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userName);
        reservation.setGuest_id(user.getId());
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservation_id) {

    }

    private Set<Offert> getOfferts(Set<Availability> availabilitiesAndReservations, Set<Long> takenRooms) {
        return availabilitiesAndReservations.stream().filter(availability -> !takenRooms.contains(availability.getRoom_id()))
                .filter(distinctByKey(p -> p.getRoom_id()))
                .map(availability -> Offert.builder()
                        .hotel_id(availability.getHotel_id())
                        .hotel_name(availability.getHotel_name())
                        .city(availability.getCity())
                        .grade(availability.getGrade())
                        .room_id(availability.getRoom_id())
                        .roomType(availability.getRoomType())
                        .reservation_id(availability.getReservation_id())
                        .from_date(availability.getFrom_date())
                        .to_date(availability.getTo_date())
                        .price(calculatePriceAlgorithm.getPrice(availability))
                        .build())
                .collect(Collectors.toSet());
    }

    private Set<Long> getTakenRooms(AvailabilityRequest request, Set<Availability> availabilitiesAndReservations) {
        Set<Long> takenRooms = availabilitiesAndReservations.stream()
                .filter(availability -> availabilityCheckProcessingAlgorithm.checkAvailability(availability, request))
                .map(x -> x.getRoom_id())
                .collect(Collectors.toSet());
        return takenRooms;
    }
}

