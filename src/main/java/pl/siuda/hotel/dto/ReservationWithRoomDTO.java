package pl.siuda.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.models.embeddedClasses.Address;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationWithRoomDTO {

    private Long reservationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private Room room;
    private String guestName;
    private String guestLastName;
    private String email;
    private String requestMessage;
    private ReservationStatus reservationStatus;
    private Address guestAddress;
}
