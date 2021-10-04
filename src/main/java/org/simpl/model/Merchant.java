package org.simpl.model;

public class Merchant {
    String name;
    String email;
    Double discountPercent;
    Double allTransactionsSum;

    public Merchant(String name, String email, Double discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
        this.email = email;
    }

    public Double getAllTransactionsSum() {
        return allTransactionsSum;
    }

    public void setAllTransactionsSum(Double allTransactionsSum) {
        this.allTransactionsSum = allTransactionsSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
