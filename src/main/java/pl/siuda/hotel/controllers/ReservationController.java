package pl.siuda.hotel.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.AvailabilityResponse;
import pl.siuda.hotel.models.HttpResponse;
import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.requests.ReservationRequest;
import pl.siuda.hotel.services.BookingService;
import pl.siuda.hotel.services.ReservationService;

import javax.annotation.security.RolesAllowed;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final BookingService bookingService;

    public ReservationController(ReservationService reservationService, BookingService bookingService) {
        this.reservationService = reservationService;
        this.bookingService = bookingService;
    }

    @PostMapping("/available-rooms")
    public AvailabilityResponse availableRooms(@RequestBody AvailabilityRequest request){
        return bookingService.getAvailableRooms(request);
    }

    @PostMapping("/place-a-booking")
    public void placeABooking(@RequestBody ReservationRequest request) {
        bookingService.placeABooking(request);
    }

    @PostMapping("/place-a-booking-logged-in")
    public void placeABookingLoggedIn(@RequestBody ReservationRequest request) {
        bookingService.placeABooking(request);
    }


}
