package test.properties;

import controller.AccountingController;
import controller.PropertyController;
import controller.TenantController;
import model.geography.Address;
import model.person.Tenant;
import model.property.Apartment;
import model.property.Condo;
import model.property.House;
import model.property.Property;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mkjodhani
 * @project
 * @since 12/03/23
 */
public class PropertyTest {
    public static AccountingController accountingController;
    public static PropertyController propertyController;
    public static TenantController tenantController;
    @BeforeClass
    public static void initClass(){
        accountingController = AccountingController.getAccountingController();
        propertyController =  PropertyController.getPropertyController();
        tenantController = TenantController.getTenantController();
    }
    @Test
    public void addHouse() {
        int total = propertyController.getProperties().size();
        Address address = Address.generateAddress("Saint Marc","Montreal","Quebec","H3W 2N9",2000);
        Property house = new House(address,2,1,1200,1425);
        House addedProperty = (House) accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,house);
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        assertEquals(propertyController.getProperties().size(),total + 1);
    }
    @Test
    public void addApartment() {
        Address address = Address.generateAddress("Cote vertu","Montreal","Quebec","H3W 2N9",2000);
        Property apartment = new Apartment(address,2,1,1200,1905,1425);
        Apartment addedProperty = (Apartment) accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,apartment);
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        assertEquals(addedProperty.getAptNumber(),1905);
    }
    @Test
    public void addCondo() {
        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property condo = new Condo(address,2,1,1200,26,1200);
        Condo addedProperty = (Condo)accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,condo);
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        assertEquals(addedProperty.getUnitNumber(),26);
    }
}
