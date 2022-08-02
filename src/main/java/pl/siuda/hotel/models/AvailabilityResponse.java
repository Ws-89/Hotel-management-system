package pl.siuda.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailabilityResponse {
    private String city;
    private Integer numberOfRooms;
    private Integer partySize;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Map<?, ?> data;
}
