package services.accounting;

import model.accounting.AccountingDTO;
import model.person.Tenant;
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
    private AccountingDTO accountingDTO;
    private static  AccountingService accountingService;
    private AccountingService() {
        this.accountingDTO = AccountingDTO.getDTO();
    }

    public static AccountingService getAccountingService() {
        if (accountingService == null) {
            accountingService = new AccountingService();
        }
        return accountingService;
    }
    public Tenant addTenant(String firstName, String lastName, Date dateOfBirth, String email){
        Tenant tenant = new Tenant(firstName,lastName,dateOfBirth,email);
        accountingDTO.addTenant(tenant);
        return tenant;
    };
    public Property addProperty(Property.PROPERTY_TYPE propertyType, Property property){
        accountingDTO.addProperty(propertyType,property);
        return property;
    };
    public Collection<Property> getPropertiesByType(Property.PROPERTY_TYPE propertyType){
        return accountingDTO.getPropertiesByType(propertyType).values();
    }
    public Collection<Property> getPropertiesByStatus(Property.PROPERTY_TYPE propertyType, Property.AVAILABILITY_TYPE availabilityType){
        ArrayList<Property> propertyCollection = new ArrayList<>();
        for(Property property:accountingDTO.getPropertiesByType(propertyType).values()){
            if (property.getPropertyType() == propertyType && property.getStatus() == availabilityType){
                propertyCollection.add(property);
            }
        }
        return propertyCollection;
    }
    public Collection<Tenant> getTenants(){
        return accountingDTO.getTenants().values();
    }

}
