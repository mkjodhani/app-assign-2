package services.property;

import model.data.MockData;
import model.property.Lease;
import model.property.Property;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 10/03/23
 */
public class PropertyService {
    private MockData data;
    private static PropertyService propertyService;
    public PropertyService() {
        this.data = MockData.getReference();
    }
    public static PropertyService getPropertyService(){
        if (propertyService == null) {
            propertyService = new PropertyService();
        }
        return propertyService;
    }
    public Property getPropertyByID(int propertyID){
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            Property property = this.data.getProperties().get(propertyType).get(propertyID);
            if(property != null){
                return property;
            }
        }
        return null;
    }
    public boolean updatePropertyStatus(int propertyID, Property.AVAILABILITY_TYPE availabilityType){
        Property property = getPropertyByID(propertyID);
        if (property == null){
            return false;
        }
        property.setStatus(availabilityType);
        this.data.notifyAllObservers();
        return true;
    }
    public boolean terminateLease(int leaseID){
        Lease lease =this.data.getLeases().getOrDefault(leaseID,null);
        if (lease == null){
            return false;
        }
        lease.terminateLease();
        this.data.notifyAllObservers();
        return true;
    }

    public boolean payRent(int leaseID) {
        Lease lease =this.data.getLeases().getOrDefault(leaseID,null);
        if (lease == null){
            return false;
        }
        lease.payRent();
        this.data.notifyAllObservers();
        return true;
    }

    public Lease getLeaseById(int leaseID) {
        return this.data.getLeases().getOrDefault(leaseID,null);
    }
    public Collection<Property> getPropertiesByStatus(Property.PROPERTY_TYPE propertyType, Property.AVAILABILITY_TYPE availabilityType){
        ArrayList<Property> propertyCollection = new ArrayList<>();
        for(Property property: data.getProperties().get(propertyType).values()){
            if (property.getStatus() == availabilityType){
                propertyCollection.add(property);
            }
        }
        return propertyCollection;
    }

    public Collection<Property> getAll() {
        ArrayList<Property> propertyCollection = new ArrayList<>();
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            for(Property property: data.getProperties().get(propertyType).values()){
                propertyCollection.add(property);
            }
        }
        return propertyCollection;
    }
    public Property addProperty(Property.PROPERTY_TYPE propertyType, Property property){
        this.data.getProperties().get(propertyType).put(property.getPropertyId(),property);
        this.data.notifyAllObservers();
        return property;
    };
}
