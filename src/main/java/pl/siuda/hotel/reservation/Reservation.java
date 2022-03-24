package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Reservation {

    @Id
    private Long reservation_id;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private Long room_id;
    private BigDecimal price;
    private String email;

    public Reservation(LocalDateTime from_date, LocalDateTime to_date, Long room_id, BigDecimal price, String email) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.room_id = room_id;
        this.price = price;
        this.email = email;

    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Reservation() {
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

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Long getId() {
        return reservation_id;
    }

    public void setId(Long id) {
        this.reservation_id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
