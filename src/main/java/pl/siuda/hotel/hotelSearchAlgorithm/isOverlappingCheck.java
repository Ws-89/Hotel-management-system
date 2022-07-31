package pl.siuda.hotel.hotelSearchAlgorithm;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.models.embeddedClasses.BookingDetails;
import pl.siuda.hotel.dto.AvailabilityRequest;

@Component
public class isOverlappingCheck implements AvailabilityCheckProcessingAlgorithm{

    @Override
    public boolean isOverlapping(BookingDetails availability, AvailabilityRequest availabilityRequest) {
        if(availability.getStartDate() != null || availability.getEndDate() != null)
            return  isRequestStartDateInsideAnyReservationDate(availability, availabilityRequest)
                    || (isRequestEndDateInsideReservationDate(availability, availabilityRequest))
                    || isRequestOutsideOfReservationDate(availability, availabilityRequest)
                    || isRequestDateInsideReservationDate(availability, availabilityRequest);
        else
            return false;
    }

    boolean isRequestStartDateInsideAnyReservationDate(BookingDetails availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getStartDate().isBefore(availability.getEndDate()) || request.getStartDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestEndDateInsideReservationDate(BookingDetails availability, AvailabilityRequest request){
        return (request.getEndDate().isAfter(availability.getStartDate()) || request.getEndDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestOutsideOfReservationDate(BookingDetails availability, AvailabilityRequest request){
        return (request.getStartDate().isBefore(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isAfter(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }

    boolean isRequestDateInsideReservationDate(BookingDetails availability, AvailabilityRequest request){
        return (request.getStartDate().isAfter(availability.getStartDate()) || request.getStartDate().isEqual(availability.getStartDate()))
                && (request.getEndDate().isBefore(availability.getEndDate()) || request.getEndDate().isEqual(availability.getEndDate()));
    }
}
