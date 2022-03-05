package pl.siuda.hotel.reservation.hotelSearchAlgorithm;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

@Component
public class EndsOfCompartmentCheckingAlgorithm  implements AvailabilityCheckProcessingAlgorithm{

    @Override
    public boolean checkAvailability(Availability availability, AvailabilityRequest availabilityRequest) {
        if(availability.getFrom_date() != null || availability.getTo_date() != null)
            return  isRequestStartDateInsideAnyReservationDate(availability, availabilityRequest)
                    || (isRequestEndDateInsideReservationDate(availability, availabilityRequest))
                    || isRequestOutsideOfReservationDate(availability, availabilityRequest)
                    || isRequestDateInsideReservationDate(availability, availabilityRequest);
        else
            return false;
    }

    boolean isRequestStartDateInsideAnyReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getFrom_date().isAfter(availability.getFrom_date()) || request.getFrom_date().isEqual(availability.getFrom_date()))
                && (request.getFrom_date().isBefore(availability.getTo_date()) || request.getFrom_date().isEqual(availability.getTo_date()));
    }

    boolean isRequestEndDateInsideReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getTo_date().isAfter(availability.getFrom_date()) || request.getTo_date().isEqual(availability.getFrom_date()))
                && (request.getTo_date().isBefore(availability.getTo_date()) || request.getTo_date().isEqual(availability.getTo_date()));
    }

    boolean isRequestOutsideOfReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getFrom_date().isBefore(availability.getFrom_date()) || request.getFrom_date().isEqual(availability.getFrom_date()))
                && (request.getTo_date().isAfter(availability.getTo_date()) || request.getTo_date().isEqual(availability.getTo_date()));
    }

    boolean isRequestDateInsideReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getFrom_date().isAfter(availability.getFrom_date()) || request.getFrom_date().isEqual(availability.getFrom_date()))
                && (request.getTo_date().isBefore(availability.getTo_date()) || request.getTo_date().isEqual(availability.getTo_date()));
    }
}
