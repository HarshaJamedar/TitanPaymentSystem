package edu.csuf;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@ToString
public class Purchase implements Serializable, PrintItem {
    Card purchaseCard;
    Date date;
    Double amount;
    String billingCycle;
    Boolean isPaid;

    public Purchase(Date date, Card purchaseCard, double amount, Boolean isPaid) {
        this.date = date;
        this.amount = amount;
        this.purchaseCard = purchaseCard;
//        LocalDate from Date object
        this.billingCycle = Utils.getBillingCycle(date);
        this.isPaid = isPaid;
        if (!isPaid) {
            this.amount += amount * (0.002 + Utils.cardMap.get(purchaseCard));
        }
    }


    @Override
    public String getTransactionType() {
        return "Purchase";
    }
}
