package controller;

import model.person.Tenant;
import model.property.Lease;
import model.property.Property;
import services.accounting.AccountingService;

import java.util.Date;
import java.util.Collection;

public class AccountingController {
    private static AccountingService accountingService;
    AccountingController(){
        accountingService = AccountingService.getAccountingService();
    }
    public Tenant addTenant(String firstName, String lastName, Date dateOfBirth, String email){
        return accountingService.addTenant(firstName, lastName,  dateOfBirth,  email);
    }
    public Property addProperty(Property.PROPERTY_TYPE propertyType, Property property){
        return accountingService.addProperty(propertyType, property);
    }
    public Collection<Property> getPropertiesByType(Property.PROPERTY_TYPE propertyType){
        return accountingService.getPropertiesByType(propertyType);
    }
    public Collection<Property> getPropertiesByStatus(Property.PROPERTY_TYPE propertyType, Property.AVAILABILITY_TYPE availabilityType){
        return accountingService.getPropertiesByStatus(propertyType,availabilityType);
    }
    public Collection<Tenant> getTenants(){
        return accountingService.getTenants();
    }
    public int rentUnit(int tenantID,int propertyID){
        return accountingService.rentUnit(tenantID, propertyID);
    }
    public Lease getLease(int leaseId){
        return accountingService.getLease(leaseId);
    }
    public Collection<Lease> getLeases(){
        return accountingService.getLeases();
    }
    public boolean subscribeProperty(int tenantID, int propertyID) {
        return accountingService.subscribeProperty(tenantID, propertyID);
    }
}
