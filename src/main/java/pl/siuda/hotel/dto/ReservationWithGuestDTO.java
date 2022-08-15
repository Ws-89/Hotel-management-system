package pl.siuda.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.models.embeddedClasses.Address;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationWithGuestDTO {
        private UUID reservationId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private BigDecimal price;
        private Guest guest;
        private String guestName;
        private String guestLastName;
        private String email;
        private String requestMessage;
        private ReservationStatus reservationStatus;
        private Address guestAddress;

    }
