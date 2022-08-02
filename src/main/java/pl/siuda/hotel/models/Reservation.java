package pl.siuda.hotel.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reservation")
@Builder
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "tbl_reservation_sequence", sequenceName = "tbl_reservation_sequence", allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "tbl_reservation_sequence"
    )
    @Column(name = "reservation_id")
    private Long reservationId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public Reservation() {
    }

    public Reservation(Long reservationId, Room room, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        this.reservationId = reservationId;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
