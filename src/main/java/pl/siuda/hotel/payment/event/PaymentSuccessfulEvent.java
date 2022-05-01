package pl.siuda.hotel.payment.event;

import java.math.BigDecimal;

public class PaymentSuccessfulEvent {

    String name;
    BigDecimal amount;
    Boolean condition;

    public PaymentSuccessfulEvent(String name, BigDecimal amount, Boolean condition) {
        this.name = name;
        this.amount = amount;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }
}
