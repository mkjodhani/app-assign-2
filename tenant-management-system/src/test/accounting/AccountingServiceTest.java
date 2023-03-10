package test.accounting;

import model.geography.Address;
import model.person.Tenant;
import model.property.Condo;
import model.property.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.accounting.AccountingService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mkjodhani
 * @project
 * @since 07/03/23
 */
class AccountingServiceTest {

    private static AccountingService accountingService;
    @BeforeAll
    private static void init(){
        accountingService = AccountingService.getAccountingService();
    }
    @Test
    void addTenant() {
        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingService.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getId(),1);
        assertEquals(addedTenant.getEmail(),"mkjodhani133@gmail.com");
        assertEquals(addedTenant.getDateOfBirth(),dateOfBirth);
        assertEquals(addedTenant.getFirstName(),"Mayur");
        assertEquals(addedTenant.getLastName(),"Jodhani");
        assertEquals(addedTenant.getClass().getName(),Tenant.class.getName());
        addedTenant.show();
    }

    @Test
    void addProperty() throws CloneNotSupportedException {
        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26);
        Condo addedProperty = (Condo)accountingService.addProperty(Property.PROPERTY_TYPE.CONDO,property);
        assertEquals(addedProperty.getPropertyId(),"CONDO00001","Property ID not matched");
        assertEquals(addedProperty.getAddress(),address);
        assertEquals(addedProperty.getSquareFootage(),1200);
        assertEquals(addedProperty.getNumberOfBedrooms(),2);
        assertEquals(addedProperty.getNumberOfBathrooms(),1);
        assertEquals(addedProperty.getUnitNumber(),26);
        assertEquals(accountingService.getPropertiesByType(Property.PROPERTY_TYPE.CONDO).size(),1);
        assertNotEquals(addedProperty,addedProperty.getClone());
        property.show();
    }

}