package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Reservation {

    @Id
    private Long reservation_id;
    private LocalDateTime from_data;
    private LocalDateTime to_data;
    private Integer partySize;


    public Reservation() {
    }

    public LocalDateTime getFrom_data() {
        return from_data;
    }

    public void setFrom_data(LocalDateTime from_data) {
        this.from_data = from_data;
    }

    public LocalDateTime getTo_data() {
        return to_data;
    }

    public void setTo_data(LocalDateTime to_data) {
        this.to_data = to_data;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }


    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public Long getId() {
        return reservation_id;
    }

    public void setId(Long id) {
        this.reservation_id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
