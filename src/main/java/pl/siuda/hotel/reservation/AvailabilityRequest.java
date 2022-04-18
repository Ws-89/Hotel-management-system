package pl.siuda.hotel.reservation;

import java.time.LocalDateTime;

public class AvailabilityRequest {

    private String city;
    private Integer numberOfRooms;
    private Integer partySize;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public AvailabilityRequest(String city, Integer numberOfRooms, Integer partySize, LocalDateTime startDate, LocalDateTime endDate) {
        this.city = city;
        this.numberOfRooms = numberOfRooms;
        this.partySize = partySize;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
