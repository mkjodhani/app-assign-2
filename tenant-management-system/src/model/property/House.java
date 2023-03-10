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
    public House(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage) {
        super(address, numberOfBedrooms, numberOfBathrooms,squareFootage, AVAILABILITY_TYPE.READY_TO_BE_RENOVATED, PROPERTY_TYPE.HOUSE);
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void update() {

    }
    @Override
    public String getFullAddress() {
        return super.getAddress().toString();
    }
}
