package services.property;

import model.data.MockData;
import model.property.Property;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 10/03/23
 */
public class PropertyService {
    private MockData data;

    public PropertyService() {
        this.data = MockData.getReference();
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
        return true;
    }
}
