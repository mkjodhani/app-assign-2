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
public abstract class Property extends Observable implements Cloneable {
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
    private double squareFootage;
    private AVAILABILITY_TYPE status;
    private PROPERTY_TYPE propertyType;

    public Property(Address address, int numberOfBedrooms, int numberOfBathrooms, double squareFootage, AVAILABILITY_TYPE availabilityType, PROPERTY_TYPE propertyType) {
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFootage = squareFootage;
        this.propertyId = ++totalProperty;
        this.propertyType = propertyType;
        this.status = availabilityType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public String getPropertyId() {
        return propertyType + String.format("%5d",this.propertyId).replace(" ","0");
    }

    public abstract void addListener(InvalidationListener listener);

    public abstract void removeListener(InvalidationListener listener);

    public abstract void update();

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
        table.addRow("Property Id",this.getPropertyId(), Table.POSITION.LEFT);
        table.addRow("Square Footage",String.valueOf(this.squareFootage), Table.POSITION.LEFT);
        table.addRow("Status",String.valueOf(status), Table.POSITION.LEFT);
        table.addRow("Property Type",String.valueOf(propertyType), Table.POSITION.LEFT);
        table.show();
    }

    public abstract String getFullAddress();
}
