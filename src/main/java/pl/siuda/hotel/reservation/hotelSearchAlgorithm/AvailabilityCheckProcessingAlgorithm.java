package pl.siuda.hotel.reservation.hotelSearchAlgorithm;

import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

public interface AvailabilityCheckProcessingAlgorithm  {

    boolean checkAvailability(Availability availability, AvailabilityRequest availabilityRequest);
}
