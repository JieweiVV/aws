package com.jiewei.aws;

import java.math.BigDecimal;

public class PostToken {
    private BigDecimal amount;
    public PostToken(BigDecimal amount) {
        this.amount = amount;
    }

    public PostToken() {
        amount = null;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    
}