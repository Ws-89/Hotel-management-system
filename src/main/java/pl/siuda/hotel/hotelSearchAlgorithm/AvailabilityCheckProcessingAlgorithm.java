package pl.siuda.hotel.hotelSearchAlgorithm;

import pl.siuda.hotel.models.embeddedClasses.BookingDetails;
import pl.siuda.hotel.dto.AvailabilityRequest;

public interface AvailabilityCheckProcessingAlgorithm  {

    boolean isOverlapping(BookingDetails availability, AvailabilityRequest availabilityRequest);
}
