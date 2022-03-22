package pl.siuda.hotel.reservation.hotelSearchAlgorithm;

import org.junit.jupiter.api.Test;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OverlappingCheckTest {

    @Test
    void checkAvailability() {
        OverlappingCheck algorithm = new OverlappingCheck();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, "",2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01), BigDecimal.valueOf(1));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, 6, 00, 00, 01),
                LocalDateTime.of(2022, 05, 11, 00, 00, 01));

        assertThat(algorithm.checkAvailability(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestStartDateInsideAnyReservationDate() {
        OverlappingCheck algorithm = new OverlappingCheck();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, "",2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01), BigDecimal.valueOf(1));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("09"), 00, 00, 01));

        assertThat(algorithm.isRequestStartDateInsideAnyReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestEndDateInsideReservationDate() {
        OverlappingCheck algorithm = new OverlappingCheck();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, "",2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01), BigDecimal.valueOf(1));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("06"), 00, 00, 01));

        assertThat(algorithm.isRequestEndDateInsideReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestOutsideOfReservationDate() {
        OverlappingCheck algorithm = new OverlappingCheck();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, "",2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01), BigDecimal.valueOf(1));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("07"), 00, 00, 01));

        assertThat(algorithm.isRequestOutsideOfReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestDateInsideReservationDate() {
        OverlappingCheck algorithm = new OverlappingCheck();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, "",2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01), BigDecimal.valueOf(1));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("04"), 00, 00, 01));

        assertThat(algorithm.isRequestDateInsideReservationDate(availability, request)).isEqualTo(true);
    }
}