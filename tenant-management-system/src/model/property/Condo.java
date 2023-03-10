package model.property;

import javafx.beans.InvalidationListener;
import model.geography.Address;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Condo extends Property{
    private int unitNumber;

    public Condo(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, int unitNumber) {
        super(address, numberOfBedrooms, numberOfBathrooms, squareFootage, AVAILABILITY_TYPE.READY_TO_BE_RENOVATED,PROPERTY_TYPE.CONDO);
        this.unitNumber = unitNumber;
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

    public int getUnitNumber() {
        return unitNumber;
    }
    @Override
    public String getFullAddress() {
        return String.format("Apt %d, %s",unitNumber,super.getAddress().toString());
    }

    public Property getClone() throws CloneNotSupportedException {
        return (Property) this.clone();
    }
}
