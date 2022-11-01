package edu.csuf;

import java.util.Date;

public interface PrintItem {
    String billingCycle = "";

    Card getPurchaseCard();

    Double getAmount();

    String getBillingCycle();

    String getTransactionType();

    Date getDate();

}
