package pl.siuda.hotel.reservation;

import org.springframework.data.relational.core.mapping.Table;

@Table("room_reservation")
public class ReservationArrangement {

    private Long room_id;

    public ReservationArrangement(Long room_id) {
        this.room_id = room_id;
    }

    public Long getRoomId() {
        return room_id;
    }

}
