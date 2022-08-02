package pl.siuda.hotel.hotelSearchAlgorithm;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.models.Reservation;

@Component
public class isOverlappingCheck implements AvailabilityCheckProcessingAlgorithm{

    @Override
    public boolean isOverlapping(Reservation reservation, AvailabilityRequest availabilityRequest) {
        if(reservation.getStartDate() != null || reservation.getEndDate() != null)
            return  isRequestStartDateInsideAnyReservationDate(reservation, availabilityRequest)
                    || (isRequestEndDateInsideReservationDate(reservation, availabilityRequest))
                    || isRequestOutsideOfReservationDate(reservation, availabilityRequest)
                    || isRequestDateInsideReservationDate(reservation, availabilityRequest);
        else
            return false;
    }

    boolean isRequestStartDateInsideAnyReservationDate(Reservation availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getStartDate().isBefore(availability.getEndDate()) || request.getStartDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestEndDateInsideReservationDate(Reservation availability, AvailabilityRequest request){
        return (request.getEndDate().isAfter(availability.getStartDate()) || request.getEndDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestOutsideOfReservationDate(Reservation availability, AvailabilityRequest request){
        return (request.getStartDate().isBefore(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isAfter(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestDateInsideReservationDate(Reservation availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }
}
