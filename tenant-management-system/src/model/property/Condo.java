package model.property;

import model.geography.Address;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Condo extends Property{
    private int unitNumber;

    public Condo(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, int unitNumber, double rent) {
        super(address, numberOfBedrooms, numberOfBathrooms, squareFootage,PROPERTY_TYPE.CONDO,rent);
        this.unitNumber = unitNumber;
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
