package pl.siuda.hotel.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.dto.ReservationDTO;
import pl.siuda.hotel.models.enums.RoomType;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {
    private Long roomId;
    private String description;
    private RoomType roomType;
    private Set<ReservationDTO> reservations;
    private BigDecimal price;

}
