package pl.siuda.hotel.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequest {

    private int party_size;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private List<Integer> room_ids;
    private BigDecimal price;

    public ReservationRequest(int party_size, LocalDateTime from_date, LocalDateTime to_date, List<Integer> room_ids, BigDecimal price) {
        this.party_size = party_size;
        this.from_date = from_date;
        this.to_date = to_date;
        this.room_ids = room_ids;
        this.price = price;
    }

    public void requestToEntity(Reservation reservation){
        reservation.setPartySize(party_size);
        reservation.setFrom_date(from_date);
        reservation.setTo_date(to_date);
        for(Integer id : room_ids){
            reservation.addRoom(id);
        }
    }

}
