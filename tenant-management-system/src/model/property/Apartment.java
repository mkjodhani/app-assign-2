package model.property;

import model.geography.Address;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Apartment extends Property{
        public Apartment(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, boolean occupied) {
        super(address, numberOfBedrooms, numberOfBathrooms, squareFootage, occupied);
    }
}
