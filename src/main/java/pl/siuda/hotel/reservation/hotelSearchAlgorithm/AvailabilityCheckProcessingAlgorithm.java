package pl.siuda.hotel.reservation.hotelSearchAlgorithm;

import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

public interface AvailabilityCheckProcessingAlgorithm  {

    boolean isOverlapping(Availability availability, AvailabilityRequest availabilityRequest);
}
