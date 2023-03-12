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
        ArrayList<Tenant> tenantsWithRentPaid = tenantController.getTenantsByRentPaid(true);
        ArrayList<Tenant> tenantsWithoutRentPaid = tenantController.getTenantsByRentPaid(false);

        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingController.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");

        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo addedProperty = (Condo)accountingController.addProperty(Property.PROPERTY_TYPE.CONDO,property);


        int leaseID = accountingController.rentUnit(addedTenant.getId(),addedProperty.getPropertyId(),11);

        tenantsWithRentPaid = tenantController.getTenantsByRentPaid(true);
        assertEquals(tenantsWithRentPaid.size(),0);
        tenantsWithoutRentPaid = tenantController.getTenantsByRentPaid(false);
        assertEquals(tenantsWithoutRentPaid.size(),2);


        tenantController.payRent(leaseID);

        tenantsWithRentPaid = tenantController.getTenantsByRentPaid(true);
        assertEquals(tenantsWithRentPaid.size(),1);
        tenantsWithoutRentPaid = tenantController.getTenantsByRentPaid(false);
        assertEquals(tenantsWithoutRentPaid.size(),1);
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

}