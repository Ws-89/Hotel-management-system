package pl.siuda.hotel.hotelSearchAlgorithm;

import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.models.Reservation;

public interface AvailabilityCheckProcessingAlgorithm  {

    boolean isOverlapping(Reservation reservation, AvailabilityRequest availabilityRequest);
}
