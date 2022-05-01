package pl.siuda.hotel.payment.event;

import org.springframework.context.ApplicationEvent;
import pl.siuda.hotel.payment.EventPayment;

import java.math.BigDecimal;

public class TransactionFailureEvent {
    String name;
    BigDecimal amount;
    Boolean condition;

    public TransactionFailureEvent(String name, BigDecimal amount, Boolean condition) {
        this.name = name;
        this.amount = amount;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Boolean getCondition() {
        return condition;
    }
}
