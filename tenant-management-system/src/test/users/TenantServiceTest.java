package test.users;
import controller.AccountingController;
import controller.PropertyController;
import controller.TenantController;
import model.geography.Address;
import model.person.Tenant;
import model.property.Condo;
import model.property.Lease;
import model.property.Property;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mkjodhani
 * @project
 * @since 10/03/23
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TenantServiceTest {
    private static TenantController tenantController;
    private static AccountingController accountingController;
    private static PropertyController propertyController;

    @Before
    public void initClass(){
        tenantController = TenantController.getTenantController();
        accountingController = AccountingController.getAccountingController();
        propertyController = PropertyController.getPropertyController();
    }

    @Test
    public void getTenantsByRentPaid() {
        int totalTenantsWithPaid = tenantController.getTenantsByRentPaid(true).size(),totalTenantsWithoutPaid=tenantController.getTenantsByRentPaid(false).size();
        Date dateOfBirth = new Date();
        Tenant tenant1 = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo condo = (Condo)accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,property);

        int leaseID = accountingController.rentUnit(tenant1.getId(),condo.getPropertyId(),11);
        totalTenantsWithoutPaid++;
        assertEquals(tenantController.getTenantsByRentPaid(false).size(),totalTenantsWithoutPaid);
        assertEquals(tenantController.getTenantsByRentPaid(true).size(),totalTenantsWithPaid);

        tenantController.payRent(leaseID);
        totalTenantsWithPaid++;
        totalTenantsWithoutPaid--;
        assertEquals(tenantController.getTenantsByRentPaid(false).size(),totalTenantsWithoutPaid);
        assertEquals(tenantController.getTenantsByRentPaid(true).size(),totalTenantsWithPaid);

    }
    @Test
    public void payRent() {
        Date dateOfBirth = new Date();
        Tenant tenant = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");

        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo condo = (Condo)accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,property);


        int leaseID = accountingController.rentUnit(tenant.getId(),condo.getPropertyId(),11);

        Lease lease = propertyController.getLeaseByID(leaseID);
        assertFalse(lease.isRentPaidForCurrentMonth());

        tenantController.payRent(leaseID);
        assertTrue(lease.isRentPaidForCurrentMonth());
    }
    @Test
    public void getNotificationOncePropertyIsAvailable() {

        Date dateOfBirth = new Date();
        Tenant tenant1 = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");
        Tenant tenant2 = accountingController.addTenant("Dharmil","Vaghasiya",dateOfBirth,"vaghasiya23r@gmail.com");

        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo addedProperty = (Condo)accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,property);
        int totalMessagesBefore = tenant2.getMessages().size();
        int leaseID = accountingController.rentUnit(tenant1.getId(),addedProperty.getPropertyId(),11);
        accountingController.subscribeProperty(tenant2.getId(),addedProperty.getPropertyId());
        tenantController.payRent(leaseID);
        propertyController.terminateLease(leaseID);

        assertEquals(tenant2.getMessages().size(),totalMessagesBefore + 1);
        tenant2.showMessages();
    }

}