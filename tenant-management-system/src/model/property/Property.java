package model.property;

import javafx.beans.InvalidationListener;
import model.geography.Address;
import view.cli.helper.Table;

import java.util.Observable;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public abstract class Property extends Observable{
    public enum AVAILABILITY_TYPE {
        READY_TO_BE_RENTED,
        READY_TO_BE_RENOVATED,
        RENTED
    }
    public enum PROPERTY_TYPE {
        APARTMENT,
        CONDO,
        HOUSE
    }

    private static int totalProperty = 0 ;
    private Address address;
    private int numberOfBedrooms, numberOfBathrooms, propertyId;
    private double squareFootage, rent;
    private AVAILABILITY_TYPE status;
    private PROPERTY_TYPE propertyType;

    public Property(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, PROPERTY_TYPE propertyType, double rent) {
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFootage = squareFootage;
        this.propertyId = ++totalProperty;
        this.propertyType = propertyType;
        this.status = AVAILABILITY_TYPE.READY_TO_BE_RENTED;
        this.rent = rent;
    }

    public Address getAddress() {
        return address;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public AVAILABILITY_TYPE getStatus() {
        return status;
    }

    public PROPERTY_TYPE getPropertyType() {
        return propertyType;
    }

    public void show() {
        Table table = new Table();
        table.addRow("Address",address.toString(), Table.POSITION.LEFT);
        table.addRow("Number Of Bedrooms", String.valueOf(numberOfBedrooms), Table.POSITION.LEFT);
        table.addRow("Number Of Bathrooms",String.valueOf(numberOfBathrooms), Table.POSITION.LEFT);
        table.addRow("Property Id",String.valueOf(this.getPropertyId()), Table.POSITION.LEFT);
        table.addRow("Square Footage",String.valueOf(this.squareFootage), Table.POSITION.LEFT);
        table.addRow("Status",String.valueOf(status), Table.POSITION.LEFT);
        table.addRow("Property Type",String.valueOf(propertyType), Table.POSITION.LEFT);
        table.show();
    }

    public void setStatus(AVAILABILITY_TYPE status) {
        this.status = status;
        // only when the property is ready to be rented
        setChanged();
        notifyObservers(this);
    }

    public abstract String getFullAddress();

    public double getRent() {
        return rent;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void terminateLease(){
        setStatus(AVAILABILITY_TYPE.READY_TO_BE_RENOVATED);
    };
}
