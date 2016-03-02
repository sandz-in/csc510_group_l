package com.expensetracking.models;


public enum ExpenseSubmissionType {
    SMS("sms"),
    MANUAL("manual"),
    VOICE ("voice"),
    IMAGE ("image");

    private final String expenseType;

    private ExpenseSubmissionType(String expenseType) {
        this.expenseType=expenseType;
    }
    public String toString() {
        return this.expenseType;
    }
}
