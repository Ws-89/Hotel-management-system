package pl.siuda.hotel.reservation;

import java.time.LocalDateTime;
import java.util.Map;

public class AvailabilityRequest {

    private String city;
    private Integer roomAmmount;
    private Integer partySize;
    private LocalDateTime from;
    private LocalDateTime to;

    public AvailabilityRequest(String city, Integer roomAmmount, Integer partySize, LocalDateTime from, LocalDateTime to) {
        this.city = city;
        this.roomAmmount = roomAmmount;
        this.partySize = partySize;
        this.from = from;
        this.to = to;
    }

}
