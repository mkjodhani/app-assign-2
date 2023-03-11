package model.finanace;

import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 09/03/23
 */
public class Transaction {
    private static int totalTxns = 0;
    private Date timeStamp;
    private float amount;
    private int leaseID, txnID;

    private String month;


    public Transaction(float amount, int leaseID,String month) {
        this.txnID = ++totalTxns;
        this.amount = amount;
        this.leaseID = leaseID;
        this.month = month;
        this.timeStamp = new Date();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public float getAmount() {
        return amount;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public int getTxnID() {
        return txnID;
    }

    public String getMonth() {
        return month;
    }
}
