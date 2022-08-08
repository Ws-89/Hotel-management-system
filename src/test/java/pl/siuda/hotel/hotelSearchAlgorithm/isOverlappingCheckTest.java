package pl.siuda.hotel.hotelSearchAlgorithm;

import org.junit.jupiter.api.Test;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.models.enums.RoomType;
import pl.siuda.hotel.requests.AvailabilityRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class isOverlappingCheckTest {

    @Test
    void checkAvailability() {
        isOverlappingCheck algorithm = new isOverlappingCheck();
        Reservation reservation = Reservation.builder()
                .startDate(LocalDateTime.of(2022, 05, 02, 00, 00, 01))
                .endDate(LocalDateTime.of(2022, 05, 06, 00, 00, 01)).build();


        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, 6, 00, 00, 01),
                LocalDateTime.of(2022, 05, 11, 00, 00, 01));

        assertThat(algorithm.isOverlapping(reservation, request)).isEqualTo(true);
    }

    @Test
    void isRequestStartDateInsideAnyReservationDate() {
        isOverlappingCheck algorithm = new isOverlappingCheck();
        Reservation reservation = Reservation.builder()
                .startDate(LocalDateTime.of(2022, 05, 02, 00, 00, 01))
                .endDate(LocalDateTime.of(2022, 05, 06, 00, 00, 01)).build();

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("09"), 00, 00, 01));

        assertThat(algorithm.isRequestStartDateInsideAnyReservationDate(reservation, request)).isEqualTo(true);
    }

    @Test
    void isRequestEndDateInsideReservationDate() {
        isOverlappingCheck algorithm = new isOverlappingCheck();

        Reservation reservation = Reservation.builder()
                .startDate(LocalDateTime.of(2022, 05, 02, 00, 00, 01))
                .endDate(LocalDateTime.of(2022, 05, 06, 00, 00, 01)).build();


        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("06"), 00, 00, 01));

        assertThat(algorithm.isRequestEndDateInsideReservationDate(reservation, request)).isEqualTo(true);
    }

    @Test
    void isRequestOutsideOfReservationDate() {
        isOverlappingCheck algorithm = new isOverlappingCheck();

        Reservation reservation = Reservation.builder()
                .startDate(LocalDateTime.of(2022, 05, 02, 00, 00, 01))
                .endDate(LocalDateTime.of(2022, 05, 06, 00, 00, 01)).build();

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("07"), 00, 00, 01));

        assertThat(algorithm.isRequestOutsideOfReservationDate(reservation, request)).isEqualTo(true);
    }

    @Test
    void isRequestDateInsideReservationDate() {
        isOverlappingCheck algorithm = new isOverlappingCheck();

        Reservation reservation = Reservation.builder()
                .startDate(LocalDateTime.of(2022, 05, 02, 00, 00, 01))
                .endDate(LocalDateTime.of(2022, 05, 06, 00, 00, 01)).build();

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("04"), 00, 00, 01));

        assertThat(algorithm.isRequestDateInsideReservationDate(reservation, request)).isEqualTo(true);
    }
}