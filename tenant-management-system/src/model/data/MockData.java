package model.data;

import model.finanace.Transaction;
import model.notificaiton.Message;
import model.person.Tenant;
import model.property.Lease;
import model.property.Property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 09/03/23
 */
public class MockData extends Observable{
    private static MockData data;
    private HashMap<Integer, Lease> leases;
    private HashMap<Integer, Tenant> tenants;
    private HashMap<Property.PROPERTY_TYPE,HashMap<Integer, Property>> properties;
    private HashMap<Integer, Transaction> transactions;
    private HashMap<Integer, Message> messages;

    private MockData() {
        leases = new HashMap<>();
        tenants = new HashMap<>();
        properties = new HashMap<>();
        transactions = new HashMap<>();
        messages = new HashMap<>();
        properties.put(Property.PROPERTY_TYPE.APARTMENT,new HashMap<>());
        properties.put(Property.PROPERTY_TYPE.CONDO,new HashMap<>());
        properties.put(Property.PROPERTY_TYPE.HOUSE,new HashMap<>());
    }

    public static MockData getReference(){
        if(data == null){
            data = new MockData();
        }
        return data;
    }

    public HashMap<Integer, Tenant> getTenants() {
        return tenants;
    }
    public void addLease(Lease lease){
        leases.put(lease.getLeaseId(),lease);
    }
    public HashMap<Property.PROPERTY_TYPE, HashMap<Integer, Property>> getProperties() {
        return properties;
    }

    public HashMap<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public HashMap<Integer, Lease> getLeases() {
        return leases;
    }

    public HashMap<Integer, Message> getMessages() {
        return messages;
    }

    public void notifyAllObservers(){
        setChanged();
        notifyObservers();
    }
}
