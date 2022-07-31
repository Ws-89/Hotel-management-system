package pl.siuda.hotel.models.embeddedClasses;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.models.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingDetails {
    private long hotelId;
    private String hotelName;
    private String city;
    private Grade grade;
    private String image;
    private long roomId;
    private RoomType roomType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}
