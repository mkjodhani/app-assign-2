package controller;

import model.property.Lease;
import services.property.PropertyService;
import model.property.Property;

import java.util.Collection;

public class PropertyController {
    private PropertyService propertyService;
    private static PropertyController propertyController;
    private PropertyController(){
        this.propertyService = PropertyService.getPropertyService();
    }
    public static PropertyController getPropertyController(){
        if(propertyController == null){
            propertyController = new PropertyController();
        }
        return propertyController;
    }
    public Property getPropertyByID(int propertyID){
        return propertyService.getPropertyByID(propertyID);
    }
    public Lease getLeaseByID(int leaseID){
        return propertyService.getLeaseById(leaseID);
    }
    public boolean updatePropertyStatus(int propertyID, Property.AVAILABILITY_TYPE availabilityType){
        return propertyService.updatePropertyStatus(propertyID,availabilityType);
    }
    public boolean terminateLease(int leaseID){
        return propertyService.terminateLease(leaseID);
    }

    public boolean payRent(int leaseID) {
        return propertyService.payRent(leaseID);
    }

    public Collection<Property> getPropertiesByStatus(Property.PROPERTY_TYPE propertyType, Property.AVAILABILITY_TYPE availabilityType) {
        return propertyService.getPropertiesByStatus(propertyType,availabilityType);
    }
    public Collection<Property> getProperties() {
        return propertyService.getAll();
    }
}
