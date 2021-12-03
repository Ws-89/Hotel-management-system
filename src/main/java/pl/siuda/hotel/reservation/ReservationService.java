package pl.siuda.hotel.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.room.RoomService;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservation {

    @Autowired
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomService roomService, ReservationRepository reservationRepository) {
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;

    }

    public Set<Availability> getRoomsByCity(String city){
        return reservationRepository.findRoomsByCity(city);
    }

    @Override
    public Set<Availability> getAvailability(AvailabilityRequest request){
        Set<Availability> availabilitySet = reservationRepository.findRoomsByCity(request.getCity());

        /* This predicate test if start or end of reservation request is placed between any existing reservation */

        Predicate<Availability> isDateAvailable = (availability) -> {
            if(availability.getTo_date() != null){
                // this boolean checks as follows :
                boolean isStartDateBetweenAnyReservation =
                        // if start of existsing reservation is after or is equal to start of reservation request
                        (availability.getFrom_date().isAfter(request.getFrom_date()) || availability.getFrom_date().isEqual(request.getFrom_date()))
                        // and if start of existing reservation is before or is equal to end of reservation request
                        && (availability.getFrom_date().isBefore(request.getTo_date()) || availability.getFrom_date().isEqual(request.getTo_date()));
                // this boolean checks as follows :
                boolean isEndDateBetweenAnyReservation =
                        // if end of existsing reservation is after or is equal to end of reservation request
                        (availability.getTo_date().isAfter(request.getFrom_date()) || availability.getTo_date().isEqual(request.getFrom_date()))
                        // and if end of existing reservation is before or is equal to end of reservation request
                        && (availability.getTo_date().isBefore(request.getTo_date()) || availability.getTo_date().isEqual(request.getTo_date()));

                return !(isStartDateBetweenAnyReservation || isEndDateBetweenAnyReservation);
            } else {
                return true;
            }
        };
        // returns set of possible reservation instances
        return availabilitySet.stream().filter(isDateAvailable).collect(Collectors.toSet());
    }

    @Override
    public Reservation makeReservation(ReservationRequest request) {

        return null;
    }

    @Override
    public void cancelReservation() {

    }
}
