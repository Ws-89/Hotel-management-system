package pl.siuda.hotel.requests;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.Room;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private Integer partySize;
    private Integer numberOfRooms;
    private Room room;
    private String email;
    private boolean confirmed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String currency;

}
