package pl.siuda.hotel.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.reservation.hotelSearchAlgorithm.AvailabilityCheckProcessingAlgorithm;
import pl.siuda.hotel.room.RoomService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservation {

    @Autowired
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;
    private AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm;

    public ReservationService(RoomService roomService, ReservationRepository reservationRepository, AvailabilityCheckProcessingAlgorithm availabilityCheckProcessingAlgorithm) {
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
        this.availabilityCheckProcessingAlgorithm = availabilityCheckProcessingAlgorithm;
    }

    public Set<Availability> getRoomsByCity(String city) {
        return reservationRepository.findRoomsByCity(city);
    }

    @Override
    public Set<Availability> getAvailability(AvailabilityRequest request){
        Set<Availability> allTheRoomsInRequestedCity = reservationRepository.findRoomsByCity(request.getCity());
        return allTheRoomsInRequestedCity.stream().filter(availability -> availabilityCheckProcessingAlgorithm.checkAvailability(availability, request)).collect(Collectors.toSet());
    }


    @Override
    public Reservation makeReservation(ReservationRequest request) {

        return null;
    }

    @Override
    public void cancelReservation(Long reservation_id) {

    }
}

