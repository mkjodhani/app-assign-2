package model.person;

import model.property.Property;

import java.util.Date;
import java.util.Observable;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Admin extends Person{
    public Admin(String firstName, String lastName, Date dateOfBirth, String email) {
        super(firstName, lastName, dateOfBirth, email);
    }

    @Override
    public void update(Observable o, Object arg) {
        Property property = (Property) arg;
        property.show();
    }
}
