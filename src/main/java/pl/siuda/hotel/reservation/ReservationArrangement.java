package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("room_reservation")
public class ReservationArrangement {

    private Long reservation;

    public ReservationArrangement(Long reservation) {
        this.reservation = reservation;
    }

    public Long getReservation() {
        return reservation;
    }

}
