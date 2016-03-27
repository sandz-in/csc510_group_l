package com.expensetracking.models;


public class ExpenseItem {
    private String billAmount;
    private String billDesc;
    private final String initBillDesc;
    private final String billType;

    // Telemetry counters
    private Integer amtDelCounts;
    private Integer amtEditCounts;
    private Integer descDelCounts;
    private Integer descEditCounts;

    private Double txnDuration;

    public ExpenseItem(String initBillDesc, String billType) {
        this.billType=billType;
        this.initBillDesc=initBillDesc;
        amtDelCounts=0;
        amtEditCounts=0;
        descDelCounts=0;
        descEditCounts=0;
        txnDuration=0.0;
    }

    // Setters
    public void setBillDesc(String billDesc) {
        this.billDesc=billDesc;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount=billAmount;
    }
    public void setTxnDuration(Double txn) {
        this.txnDuration=txn;
    }

    public void setAmtDelCounts(Integer amtDel) {
        this.amtDelCounts=amtDel;
    }
    public void setAmtEditCounts(Integer amtEdit) {
        this.amtEditCounts=amtEdit;
    }

    // Getters
    public String getBillAmount() {
        return billAmount;
    }

    public String getBillDesc() {
        return billDesc;
    }

    public String getInitBillDesc() {
        return initBillDesc;
    }

    public String getBillType() {
        return billType;
    }

    public String getAmtDelCounter() {
        return amtDelCounts.toString();
    }

    public String getAmtEditCounter() {
        return amtEditCounts.toString();
    }

    public String getDescDelCounter() {
        return descDelCounts.toString();
    }

    public String getDescEditCounter() {
        return descEditCounts.toString();
    }

    public double getTxnDuration() {
        return this.txnDuration;
    }

    public void incrementAmtEditCounterBy(Integer inc) {
        amtEditCounts+=inc;
    }

    public void incrementAmtDelCounterBy(Integer inc) {
        amtDelCounts+=inc;
    }

    public void incrementDescEditCounterBy(Integer inc) {
        descEditCounts+=inc;
    }

    public void incrementDescDelCounterBy(Integer inc) {
        descDelCounts+=inc;
    }

}
