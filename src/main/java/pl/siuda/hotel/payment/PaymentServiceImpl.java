package pl.siuda.hotel.payment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.payment.event.TransactionFailureEvent;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final ApplicationEventPublisher eventPublisher;

    public PaymentServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void sendMoney(EventPayment eventPayment) {

        try {
            if(eventPayment.condition == true){
                throw new RuntimeException("Transaction failed");
            }

            System.out.println("Hi" + eventPayment.name);
            System.out.println("sending amount " + eventPayment.amount + " is successful");
        }catch (Exception e) {
            eventPublisher.publishEvent(new TransactionFailureEvent(eventPayment.getName(), eventPayment.getAmount(), eventPayment.getCondition()));
            e.printStackTrace();
        }
    }
}
