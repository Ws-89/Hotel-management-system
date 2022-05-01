package pl.siuda.hotel.payment;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.reservation.ReservationArrangement;

public class CheckoutPayment {


    @Id
    private Long paymentId;
    private String currency;
    private Long amount;
    @MappedCollection(idColumn = "RESERVATION_ARRANGEMENT_ID")
    private ReservationArrangement reservationArrangement;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


}
