package pl.siuda.hotel.reservation.hotelSearchAlgorithm;

import org.junit.jupiter.api.Test;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Availability;
import pl.siuda.hotel.reservation.AvailabilityRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EndsOfCompartmentCheckingAlgorithmTest {

    @Test
    void checkAvailability() {
        EndsOfCompartmentCheckingAlgorithm algorithm = new EndsOfCompartmentCheckingAlgorithm();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, 2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, 6, 00, 00, 01),
                LocalDateTime.of(2022, 05, 11, 00, 00, 01));

        assertThat(algorithm.checkAvailability(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestStartDateInsideAnyReservationDate() {
        EndsOfCompartmentCheckingAlgorithm algorithm = new EndsOfCompartmentCheckingAlgorithm();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, 2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("09"), 00, 00, 01));

        assertThat(algorithm.isRequestStartDateInsideAnyReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestEndDateInsideReservationDate() {
        EndsOfCompartmentCheckingAlgorithm algorithm = new EndsOfCompartmentCheckingAlgorithm();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, 2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("06"), 00, 00, 01));

        assertThat(algorithm.isRequestEndDateInsideReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestOutsideOfReservationDate() {
        EndsOfCompartmentCheckingAlgorithm algorithm = new EndsOfCompartmentCheckingAlgorithm();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, 2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("01"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("07"), 00, 00, 01));

        assertThat(algorithm.isRequestOutsideOfReservationDate(availability, request)).isEqualTo(true);
    }

    @Test
    void isRequestDateInsideReservationDate() {
        EndsOfCompartmentCheckingAlgorithm algorithm = new EndsOfCompartmentCheckingAlgorithm();
        Availability availability = new Availability(1L, "Flex", "Leiden", Grade.THREESTARS, 2L, RoomType.DOUBLE, 1L,
                LocalDateTime.of(2022, 05, 02, 00, 00, 01),
                LocalDateTime.of(2022, 05, 06, 00, 00, 01));

        AvailabilityRequest request = new AvailabilityRequest("Leiden", 2, 2,
                LocalDateTime.of(2022, 05, Integer.parseInt("02"), 00, 00, 01),
                LocalDateTime.of(2022, 05,  Integer.parseInt("04"), 00, 00, 01));

        assertThat(algorithm.isRequestDateInsideReservationDate(availability, request)).isEqualTo(true);
    }
}