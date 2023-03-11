package model.person;

import model.property.Property;

import java.util.*;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Tenant extends Person implements Observer {
    private HashMap<Integer,Property> interestedProperties;
    public Tenant(String firstName, String lastName, Date dateOfBirth, String email) {
        super(firstName, lastName, dateOfBirth, email);
        interestedProperties = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        Property property = (Property) arg;
        if(property.getStatus() != Property.AVAILABILITY_TYPE.RENTED ){
            interestedProperties.put(property.getPropertyId(),property);
        }
        else {
            interestedProperties.remove(property.getPropertyId());
        }
    }

    public HashMap<Integer, Property> getInterestedProperties() {
        return interestedProperties;
    }
}
