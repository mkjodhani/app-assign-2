package test.accounting;

import model.geography.Address;
import model.person.Tenant;
import model.property.Condo;
import model.property.Lease;
import model.property.Property;
import org.junit.BeforeClass;
import org.junit.Test;
import services.accounting.AccountingService;
import services.property.PropertyService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mkjodhani
 * @project
 * @since 07/03/23
 */
public class AccountingServiceTest {

    public static Tenant tenant;
    public static Property property;
    public static AccountingService accountingService;
    public static PropertyService propertyService;
    public static int leaseId;

    @BeforeClass
    public static void initClass(){
        accountingService = AccountingService.getAccountingService();
        propertyService = new PropertyService();
        Date dateOfBirth = new Date();
        tenant = accountingService.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property1 = new Condo(address,2,1,1200,26,1200);
        property = accountingService.addProperty(Property.PROPERTY_TYPE.CONDO,property1);
    }
    @Test
    public void addTenant() {
        accountingService = AccountingService.getAccountingService();
        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingService.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getId(),2);
        assertEquals(addedTenant.getEmail(),"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getDateOfBirth(),dateOfBirth);
        assertEquals(addedTenant.getFirstName(),"Mayur");
        assertEquals(addedTenant.getLastName(),"Jodhani");
        assertEquals(addedTenant.getClass().getName(),Tenant.class.getName());
        this.tenant = addedTenant;
        addedTenant.show();
    }
    @Test
    public void addProperty() throws CloneNotSupportedException {
        accountingService = AccountingService.getAccountingService();
        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo addedProperty = (Condo)accountingService.addProperty(Property.PROPERTY_TYPE.CONDO,property);
        assertEquals(addedProperty.getPropertyId(),2,"Property ID not matched");
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        assertEquals(addedProperty.getUnitNumber(),26);
        assertEquals(accountingService.getPropertiesByType(Property.PROPERTY_TYPE.CONDO).size(),2);
        property.show();
        this.property = property;
    }
    @Test
    public void rentUnit(){
        leaseId = accountingService.rentUnit(tenant.getId(),property.getPropertyId());
        tenant.show();
        property.show();
        assertTrue(leaseId>=0);
        Lease lease = accountingService.getLease(leaseId);
        lease.showInDetails();
    }
    @Test
    public void subscribeProperty(){
        accountingService.subscribeProperty(tenant.getId(),property.getPropertyId());
        assertEquals(tenant.getInterestedProperties().size(),0);
//        property.terminateLease();
        propertyService.updatePropertyStatus(property.getPropertyId(), Property.AVAILABILITY_TYPE.READY_TO_BE_RENTED);
        assertEquals(tenant.getInterestedProperties().size(),1);
    }
}