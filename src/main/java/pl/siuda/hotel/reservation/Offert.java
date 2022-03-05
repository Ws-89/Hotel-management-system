package pl.siuda.hotel.reservation;

import lombok.Builder;
import lombok.Data;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class Offert {
    private long hotel_id;
    private String hotel_name;
    private String city;
    private Grade grade;
    private long room_id;
    private RoomType roomType;
    private Long reservation_id;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private BigDecimal price;
}
