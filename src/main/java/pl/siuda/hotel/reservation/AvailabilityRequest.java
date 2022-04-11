package pl.siuda.hotel.reservation;

import java.time.LocalDateTime;

public class AvailabilityRequest {

    private String city;
    private Integer numberOfRooms;
    private Integer partySize;
    private LocalDateTime from_date;
    private LocalDateTime to_date;

    public AvailabilityRequest(String city, Integer numberOfRooms, Integer partySize, LocalDateTime from_date, LocalDateTime to_date) {
        this.city = city;
        this.numberOfRooms = numberOfRooms;
        this.partySize = partySize;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public AvailabilityRequest() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumberOfRooms() { return numberOfRooms; }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public LocalDateTime getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDateTime from_date) {
        this.from_date = from_date;
    }

    public LocalDateTime getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDateTime to_date) {
        this.to_date = to_date;
    }
}
