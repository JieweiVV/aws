package com.example.myapp.DDBTest;

public enum PszTokenType {

    /**
     * Card token type.
     */
    Card("Card"),
    /**
     * Bank account token type.
     */
    BankAccount("BankAccount");

    private final String lumosTokenType;

    PszTokenType(String lumosTokenType) {
        this.lumosTokenType = lumosTokenType;
    }

    public String getLumosTokenType() {
        return this.lumosTokenType;
    }
}
