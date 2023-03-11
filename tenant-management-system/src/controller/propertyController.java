package controller;

import services.property.PropertyService;
import model.property.Property;
public class PropertyController {
    private static PropertyService propertyService;
    private static PropertyController propertyController;
    PropertyController(){
        propertyService = PropertyService.getPropertyService();
    }
    public static PropertyController getPropertyController(){
        if(propertyController== null){
            propertyController= new PropertyController();
        }
        return propertyController;
    }
    public Property getPropertyByID(int propertyID){
        return propertyService.getPropertyByID(propertyID);
    }
    public boolean updatePropertyStatus(int propertyID, Property.AVAILABILITY_TYPE availabilityType){
        return propertyService.updatePropertyStatus(propertyID,availabilityType);
    }
}
