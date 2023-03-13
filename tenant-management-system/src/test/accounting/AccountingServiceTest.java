package test.accounting;

import controller.AccountingController;
import controller.PropertyController;
import controller.TenantController;
import model.geography.Address;
import model.person.Tenant;
import model.property.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mkjodhani
 * @project
 * @since 07/03/23
 */
public class AccountingServiceTest {

    public static int tenantID,propertyID;
    public static AccountingController accountingController;
    public static PropertyController propertyController;
    public static TenantController tenantController;
    public static int leaseId;

    @BeforeClass
    public static void initClass(){
        accountingController = AccountingController.getAccountingController();
        propertyController =  PropertyController.getPropertyController();
        tenantController = TenantController.getTenantController();

        // ADD TENANT
        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getEmail(),"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getDateOfBirth(),dateOfBirth);
        assertEquals(addedTenant.getFirstName(),"Mayur");
        assertEquals(addedTenant.getLastName(),"Jodhani");
        assertEquals(addedTenant.getClass().getName(),Tenant.class.getName());
        addedTenant.show();
        tenantID = addedTenant.getId();


        Address address = Address.generateAddress("Saint Marc","Montreal","Quebec","H3W 2N9",2000);
        Property house = new House(address,2,1,1200,1425);
        House addedProperty = (House) accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,house);
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        propertyID = addedProperty.getPropertyId();
    }
    @Test
    public void addTenant() {
        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getEmail(),"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getDateOfBirth(),dateOfBirth);
        assertEquals(addedTenant.getFirstName(),"Mayur");
        assertEquals(addedTenant.getLastName(),"Jodhani");
        assertEquals(addedTenant.getClass().getName(),Tenant.class.getName());
        addedTenant.show();
    }

    @Test
    public void rentUnit(){
        Property property = propertyController.getPropertyByID(propertyID);
        Tenant tenant = tenantController.getTenantByID(propertyID);
        leaseId = accountingController.rentUnit(tenant.getId(),property.getPropertyId(),11);
        tenant.show();
        property.show();
        assertTrue(leaseId>=0);
        Lease lease = accountingController.getLease(leaseId);
        lease.showInDetails();
    }
    @Test
    public void subscribeProperty(){
        Property property = propertyController.getPropertyByID(1);
        Tenant tenant = tenantController.getTenantByID(1);
        accountingController.subscribeProperty(tenant.getId(),property.getPropertyId());
        assertEquals(tenant.getInterestedProperties().size(),0);
        propertyController.updatePropertyStatus(property.getPropertyId(), Property.AVAILABILITY_TYPE.READY_TO_BE_RENTED);
        assertEquals(tenant.getInterestedProperties().size(),1);
    }
    @Test
    public void terminateLease(){
        Property property = propertyController.getPropertyByID(propertyID);
        Tenant tenant = tenantController.getTenantByID(propertyID);
        accountingController.subscribeProperty(tenant.getId(),property.getPropertyId());
        property.terminateLease();
        Property property1 = propertyController.getPropertyByID(propertyID);
        assertEquals(property1.getStatus(), Property.AVAILABILITY_TYPE.READY_TO_BE_RENOVATED);

    }

    @Test
    public void updatePropertyStatus(){
        propertyController.updatePropertyStatus(1, Property.AVAILABILITY_TYPE.READY_TO_BE_RENOVATED);
        Property property = propertyController.getPropertyByID(1);
        assertEquals(property.getStatus(), Property.AVAILABILITY_TYPE.READY_TO_BE_RENOVATED);
    }
}