package model.property;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.data.MockData;
import model.finanace.Transaction;
import model.person.Tenant;
import view.cli.helper.Table;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Lease{
    private int leaseId;
    private static int totalLeases = 0;
    private Tenant tenant;
    private Property property;
    Date startDate, endDate;

    public Lease( Tenant tenant, Property property) {
        this.leaseId = ++totalLeases;
        this.tenant = tenant;
        this.property = property;
        this.startDate = new Date();
        this.endDate = Date.from(LocalDate.now().plusMonths(11).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Lease() {
        leaseId = ++totalLeases;
    }

    public void terminateLease(){
        property.setStatus(Property.AVAILABILITY_TYPE.READY_TO_BE_RENOVATED);
    }

    public void payRent(){
        Date today = new Date();
        int currentMonth = today.getMonth() + 1, currentYear = today.getYear();
        MockData data = MockData.getReference();
        Transaction transaction = new Transaction((float) property.getRent(),this.leaseId,getPaymentID(currentMonth,currentYear));
        data.getTransactions().put(transaction.getTxnID(),transaction);
    }

    public boolean isRentPaidForCurrentMonth(){
        Date today = new Date();
        int currentMonth = today.getMonth() + 1, currentYear = today.getYear();
        MockData data = MockData.getReference();
        for(Transaction tx: data.getTransactions().values()){
            if(tx.getMonth().equals(getPaymentID(currentMonth,currentYear)) && tx.getLeaseID() == this.leaseId){
                return true;
            }
        }
        return false;
    }
    public String getPaymentID(int month, int year){
        return String.format("%d %d",month,year);
    }

    public int getLeaseId() {
        return leaseId;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void show(){
        System.out.println("LEASE");
        Table table = new Table();
        table.addRow("Lease ID",String.valueOf(leaseId), Table.POSITION.LEFT);
        table.addRow("Start Date", startDate.toLocaleString(), Table.POSITION.LEFT);
        table.addRow("End Date",endDate.toLocaleString(), Table.POSITION.LEFT);
        table.show();
    }

    public void showInDetails(){
        this.show();
        System.out.println("TENANT");
        this.tenant.show();
        System.out.println("PROPERTY");
        this.property.show();
    }

}
