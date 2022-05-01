package pl.siuda.hotel.payment.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.siuda.hotel.payment.event.TransactionFailureEvent;

@Component
public class SendEmailListener {

    @Order(1)
    @EventListener
    public void start(TransactionFailureEvent event) {
        System.out.println("Sending email");
        System.out.println("Sending an email with body " +
                "Hi " + event.getName() + "Transaction failed for the amount " + event.getAmount());
    }

}
