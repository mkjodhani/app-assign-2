package view.cli.property;

import model.geography.Address;
import model.property.Apartment;
import model.property.Condo;
import model.property.House;
import view.cli.address.AddressCLI;
import view.cli.helper.Input;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class PropertyCLI {
    public static House genearteHouse(){
        Address address;
        int numberOfBedrooms;
        int numberOfBathrooms;
        double squareFootage;
        numberOfBedrooms = Input.getInteger("Provide number of bedrooms:");
        numberOfBathrooms = Input.getInteger("Provide number of bathrooms:");
        squareFootage = Input.getDouble("Provide square footage:");
        address = AddressCLI.generateAddress();
        return new House(address,numberOfBedrooms,numberOfBathrooms,squareFootage);
    };
    public static Apartment genearteApartment(){
        Address address;
        int numberOfBedrooms, numberOfBathrooms, aptNumber;
        double squareFootage;
        numberOfBedrooms = Input.getInteger("Provide number of bedrooms:");
        numberOfBathrooms = Input.getInteger("Provide number of bathrooms:");
        squareFootage = Input.getDouble("Provide square footage:");
        aptNumber = Input.getInteger("Provide apartment number:");
        address = AddressCLI.generateAddress();
        return new Apartment(address,numberOfBedrooms,numberOfBathrooms,squareFootage,aptNumber);
    };
    public static Condo genearteCondo(){
        Address address;
        int numberOfBathrooms, numberOfBedrooms, unitNumber;
        double squareFootage;
        numberOfBedrooms = Input.getInteger("Provide number of bedrooms:");
        numberOfBathrooms = Input.getInteger("Provide number of bathrooms:");
        squareFootage = Input.getDouble("Provide square footage:");
        unitNumber = Input.getInteger("Provide unit number:");
        address = AddressCLI.generateAddress();
        return new Condo(address,numberOfBedrooms,numberOfBathrooms,squareFootage,unitNumber);
    };
}
