package services.accounting;

import model.data.MockData;
import model.person.Tenant;
import model.property.Lease;
import model.property.Property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 06/03/23
 */
public class AccountingService {
    private MockData data;
    private static  AccountingService accountingService;
    private AccountingService() {
        this.data = MockData.getReference();
    }

    public static AccountingService getAccountingService() {
        if (accountingService == null) {
            accountingService = new AccountingService();
        }
        return accountingService;
    }
    public Tenant addTenant(String firstName, String lastName, Date dateOfBirth, String email){
        Tenant tenant = new Tenant(firstName,lastName,dateOfBirth,email);
        this.data.getTenants().put(tenant.getId(),tenant);
        this.data.notifyAllObservers();
        return tenant;
    }
    public Property addProperty(Property.PROPERTY_TYPE propertyType, Property property){
        this.data.getProperties().get(propertyType).put(property.getPropertyId(),property);
        this.data.notifyAllObservers();
        return property;
    };
    public Collection<Property> getPropertiesByType(Property.PROPERTY_TYPE propertyType){
        return data.getProperties().get(propertyType).values();
    }

    public Collection<Tenant> getTenants(){
        return data.getTenants().values();
    }

    public int rentUnit(int tenantID,int propertyID,int months){
        Property property = null;
        Tenant tenant = data.getTenants().getOrDefault(tenantID,null);
        for (Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            property = data.getProperties().get(propertyType).getOrDefault(propertyID,null);
            if (property != null){
                break;
            }
        }
        if (tenant == null || property == null){
            return -1;
        }
        else if (property.getStatus() == Property.AVAILABILITY_TYPE.RENTED){
            return -1;
        }
        Lease lease = new Lease(tenant,property,months);
        data.addLease(lease);
        property.setStatus(Property.AVAILABILITY_TYPE.RENTED);
        this.data.notifyAllObservers();
        return lease.getLeaseId();
    }
    public Lease getLease(int leaseId){
        return this.data.getLeases().getOrDefault(leaseId,null);
    }
    public Collection<Lease> getLeases(){
        return this.data.getLeases().values();
    }

    public boolean subscribeProperty(int tenantID, int propertyID){
        Tenant tenant = data.getTenants().getOrDefault(tenantID,null);
        Property property = null;
        if(tenant == null){
            return false;
        }
        for (Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            property = data.getProperties().get(propertyType).getOrDefault(propertyID,null);
            if (property != null){
                break;
            }
        }
        if (property == null){
            return false;
        }
        property.addObserver(tenant);
        return true;
    }
}
