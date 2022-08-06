package pl.siuda.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.enums.RoomType;

import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long roomId;
    private String description;
    private RoomType roomType;
    private Set<ReservationDTO> reservations;
    private BigDecimal price;
}
