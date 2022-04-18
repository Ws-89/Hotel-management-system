package pl.siuda.hotel.reservation.hotelSearchAlgorithm;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

@Component
public class isOverlappingCheck implements AvailabilityCheckProcessingAlgorithm{

    @Override
    public boolean isOverlapping(Availability availability, AvailabilityRequest availabilityRequest) {
        if(availability.getStartDate() != null || availability.getEndDate() != null)
            return  isRequestStartDateInsideAnyReservationDate(availability, availabilityRequest)
                    || (isRequestEndDateInsideReservationDate(availability, availabilityRequest))
                    || isRequestOutsideOfReservationDate(availability, availabilityRequest)
                    || isRequestDateInsideReservationDate(availability, availabilityRequest);
        else
            return false;
    }

    boolean isRequestStartDateInsideAnyReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getStartDate().isBefore(availability.getEndDate()) || request.getStartDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestEndDateInsideReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getEndDate().isAfter(availability.getStartDate()) || request.getEndDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestOutsideOfReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getStartDate().isBefore(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isAfter(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestDateInsideReservationDate(Availability availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }
}
