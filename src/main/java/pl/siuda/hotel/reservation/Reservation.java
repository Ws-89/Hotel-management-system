package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    @Id
    private Long reservationId;
    private LocalDateTime from;
    private LocalDateTime to;
    private Integer partySize;


    public Reservation() {
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

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }


    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public Long getId() {
        return reservationId;
    }

    public void setId(Long id) {
        this.reservationId = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
