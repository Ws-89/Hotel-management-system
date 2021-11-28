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

        Predicate<Availability> isDateAvailable = (availability) -> {
            if(availability.getTo_date() != null){
                boolean isStartDateBetweenAnyReservation =
                        (availability.getFrom_date().isAfter(request.getFrom())
                        || availability.getFrom_date().isEqual(request.getFrom()))
                        && (availability.getFrom_date().isBefore(request.getTo())
                                || availability.getFrom_date().isEqual(request.getTo()));

                boolean isEndDateBetweenAnyReservation =
                        (availability.getTo_date().isAfter(request.getFrom())
                        || availability.getTo_date().isEqual(request.getFrom()))
                        && (availability.getTo_date().isBefore(request.getTo())
                        || availability.getTo_date().isEqual(request.getTo()));

                return !(isStartDateBetweenAnyReservation || isEndDateBetweenAnyReservation);
            } else {
                return true;
            }
        };

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
