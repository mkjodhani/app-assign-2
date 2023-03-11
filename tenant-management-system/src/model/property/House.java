package model.property;

import javafx.beans.InvalidationListener;
import model.geography.Address;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class House extends Property{
    public House(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, double rent) {
        super(address, numberOfBedrooms, numberOfBathrooms,squareFootage, PROPERTY_TYPE.HOUSE, rent);
    }

    @Override
    public String getFullAddress() {
        return super.getAddress().toString();
    }
}
