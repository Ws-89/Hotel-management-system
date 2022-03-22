package pl.siuda.hotel.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequest {

    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private long room_id;
    private BigDecimal price;

    public ReservationRequest(LocalDateTime from_date, LocalDateTime to_date, long room_id, BigDecimal price) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.room_id = room_id;
        this.price = price;
    }

    public void requestToEntity(Reservation reservation){
        reservation.setFrom_date(from_date);
        reservation.setTo_date(to_date);
        reservation.setRoom_id(room_id);
        reservation.setPrice(price);
    }

    public LocalDateTime getFrom_date() {
        return from_date;
    }

    public LocalDateTime getTo_date() {
        return to_date;
    }

    public long getRoom_id() {
        return room_id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
