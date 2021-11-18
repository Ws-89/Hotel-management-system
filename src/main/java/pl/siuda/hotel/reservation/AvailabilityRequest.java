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

    public AvailabilityRequest() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getRoomAmmount() {
        return roomAmmount;
    }

    public void setRoomAmmount(Integer roomAmmount) {
        this.roomAmmount = roomAmmount;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
