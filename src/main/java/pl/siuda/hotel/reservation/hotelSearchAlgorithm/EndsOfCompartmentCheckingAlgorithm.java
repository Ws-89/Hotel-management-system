package pl.siuda.hotel.reservation.hotelSearchAlgorithm;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

@Component
public class EndsOfCompartmentCheckingAlgorithm  implements AvailabilityCheckProcessingAlgorithm{

    @Override
    public boolean checkAvailability(Availability availability, AvailabilityRequest availabilityRequest) {
        if(availability.getFrom_date() != null || availability.getTo_date() != null)
            return isStartDateBetweenAnyReservation(availability, availabilityRequest) && isEndDateBetweenAnyReservation(availability, availabilityRequest) ;
        else
            return true;
    }

    boolean isStartDateBetweenAnyReservation(Availability availability, AvailabilityRequest request){
        return (availability.getFrom_date().isAfter(request.getFrom_date()) || availability.getFrom_date().isEqual(request.getFrom_date()))
                && (availability.getFrom_date().isBefore(request.getTo_date()) || availability.getFrom_date().isEqual(request.getTo_date()));
    }

    boolean isEndDateBetweenAnyReservation(Availability availability, AvailabilityRequest request){
        return (availability.getTo_date().isAfter(request.getFrom_date()) || availability.getTo_date().isEqual(request.getFrom_date()))
                && (availability.getTo_date().isBefore(request.getTo_date()) || availability.getTo_date().isEqual(request.getTo_date()));
    }
}
