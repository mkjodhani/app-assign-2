package model.accounting;

import model.Model;
import model.person.Tenant;
import model.property.Property;

import java.util.HashMap;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 06/03/23
 */
public class AccountingDTO{
    private HashMap<String, Tenant> tenants;
    private HashMap<Property.PROPERTY_TYPE,HashMap<String, Property>> properties;
    private static AccountingDTO accountingDTO = null;
    private AccountingDTO() {
        tenants = new HashMap<>();
        properties = new HashMap<>();
        properties.put(Property.PROPERTY_TYPE.APARTMENT,new HashMap<>());
        properties.put(Property.PROPERTY_TYPE.CONDO,new HashMap<>());
        properties.put(Property.PROPERTY_TYPE.HOUSE,new HashMap<>());
    }

    /**
     * using factory pattern we can restrict the number of object for the model
     * always use only one reference per model in order to make data manipulation consistent
     * @return reference of Accounting Model
     */
    public static AccountingDTO getDTO() {
        if(accountingDTO == null){
            accountingDTO = new AccountingDTO();
        }
        return accountingDTO;
    }

    public void addTenant(Tenant tenant){
        tenants.put(String.valueOf(tenant.getId()),tenant);
    }
    public void addProperty(Property.PROPERTY_TYPE propertyType,Property property){
        properties.get(propertyType).put(property.getPropertyId(),property);
    }

    public HashMap<String, Property> getPropertiesByType(Property.PROPERTY_TYPE propertyType) {
        return properties.get(propertyType);
    }

    public HashMap<String, Tenant> getTenants() {
        return tenants;
    }
}
