package pl.siuda.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.enums.RoomType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomWithoutReservationsDTO {

    private Long roomId;
    private String description;
    private RoomType roomType;
    private BigDecimal price;
}
