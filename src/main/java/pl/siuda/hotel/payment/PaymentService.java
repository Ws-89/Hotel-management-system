package pl.siuda.hotel.payment;

import java.math.BigDecimal;

public interface PaymentService {

    void sendMoney(EventPayment eventPayment);
}
