package model.property;

import javafx.beans.InvalidationListener;
import model.geography.Address;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Apartment extends Property{
    private int aptNumber;

    public Apartment(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, int aptNumber, double rent) {
        super(address, numberOfBedrooms, numberOfBathrooms, squareFootage,PROPERTY_TYPE.APARTMENT,rent);
        this.aptNumber = aptNumber;
    }

    @Override
    public String getFullAddress() {
        return String.format("Apt %d, %s",aptNumber,super.getAddress().toString());
    }
}
