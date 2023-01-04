package io.swapee.swapeebackend.model;

import java.math.BigDecimal;

/**
 * @author Minoltan Issack on 12/30/2022
 */
public class Payment {
    private String id;
    private BigDecimal amount;

    public Payment() {
    }

    public Payment(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
