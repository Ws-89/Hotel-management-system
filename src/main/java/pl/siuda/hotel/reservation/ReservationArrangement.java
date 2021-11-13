package pl.siuda.hotel.reservation;

import org.springframework.data.relational.core.mapping.Table;

@Table("room_reservation")
public class ReservationArrangement {

    private Long reservation_id;

    public ReservationArrangement(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

}
