package edu.csuf;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Payment implements PrintItem {
    Double amount;
    String billingCycle;
    Card paymentCard;
    Date date;

    public Payment(Date paymentDate, Card paymentCard, Double amountPaid1) {
        this.date = paymentDate;
        this.paymentCard = paymentCard;
        this.billingCycle = Utils.getBillingCycle(paymentDate);
        this.amount = amountPaid1;
    }


    @Override
    public Card getPurchaseCard() {
        return this.paymentCard;
    }

    @Override
    public String getTransactionType() {
        return "Payment";
    }
}
