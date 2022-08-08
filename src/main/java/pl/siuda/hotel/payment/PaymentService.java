package pl.siuda.hotel.payment;

import com.stripe.exception.StripeException;
import pl.siuda.hotel.requests.ReservationRequest;

public interface PaymentService {

    void sendMoney(EventPayment eventPayment);

    String payWithStripe(ReservationRequest payment) throws StripeException;
}
