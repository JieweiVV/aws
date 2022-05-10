package com.jiewei.aws;

public class Bean {

    private double amount;
    public Bean(double amount) {
        this.amount = amount;
    }

    public Bean() {
        amount = 0;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }
}
