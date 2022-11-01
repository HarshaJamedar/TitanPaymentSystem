package edu.csuf;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class User implements Serializable {
    /*>User can create an Account
        1)Name,Fullname,Phno,Password,country,address
    ->User can query all the above fields
    ->User should be able to upload his purchases
    ->User should be able to query his minimum transaction and maximum transaction
    ->User should be able to query his Amount due ,total amount paid
    ->User should be able to retrieve the payment history(Table data to show the amount paid on
    different occasions)
    ->User should be able to display all the purchases made (use a filter to sort the purchases made
    between those days””)*/
    String name;
    String fullName;
    String phoneNumber;
    private String password;
    String country;
    String address;
    List<Purchase> purchaseList;

    List<Payment> paymentList;

    public User(String name, String fullName, String phoneNumber, String password, String country, String address) {
        this.name = name;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.country = country;
        this.address = address;
        this.purchaseList = new ArrayList<>();
        this.paymentList= new ArrayList<>();
    }

    public Double getAmountDue(String billingCycle1) {
        double amountDue = 0;
        for (Purchase purchase : purchaseList) {
            if (purchase.getBillingCycle().equals(billingCycle1) && !purchase.getIsPaid()) {
                amountDue += purchase.getAmount();
            }
        }
        return amountDue;
    }
}
