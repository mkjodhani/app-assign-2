package test.users;
import model.geography.Address;
import model.person.Tenant;
import model.property.Condo;
import model.property.Property;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import services.accounting.AccountingService;
import services.users.TenantService;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mkjodhani
 * @project
 * @since 10/03/23
 */
public class TenantServiceTest {
    private static TenantService tenantService;
    private static AccountingService accountingService;

    @Before
    public void initClass(){
        tenantService = new TenantService();
        accountingService = AccountingService.getAccountingService();
    }

    @Test
    public void getTenantsByRentPaid() {
        ArrayList<Tenant> tenantsWithRentPaid = tenantService.getTenantsByRentPaid(true);
        ArrayList<Tenant> tenantsWithoutRentPaid = tenantService.getTenantsByRentPaid(false);

        Date dateOfBirth = new Date();
        Tenant addedTenant = accountingService.addTenant("Mayur","Jodhani",dateOfBirth,"mkjodhani133@gmail.com");

        Address address = Address.generateAddress("Rue Bouchette","Montreal","Quebec","H3W 1C5",4795);
        Property property = new Condo(address,2,1,1200,26,1200);
        Condo addedProperty = (Condo)accountingService.addProperty(Property.PROPERTY_TYPE.CONDO,property);


        int leaseID = accountingService.rentUnit(addedTenant.getId(),addedProperty.getPropertyId());

        tenantsWithRentPaid = tenantService.getTenantsByRentPaid(true);
        assertEquals(tenantsWithRentPaid.size(),0);
        tenantsWithoutRentPaid = tenantService.getTenantsByRentPaid(false);
        assertEquals(tenantsWithoutRentPaid.size(),2);


        tenantService.payRent(leaseID);

        tenantsWithRentPaid = tenantService.getTenantsByRentPaid(true);
        assertEquals(tenantsWithRentPaid.size(),1);
        tenantsWithoutRentPaid = tenantService.getTenantsByRentPaid(false);
        assertEquals(tenantsWithoutRentPaid.size(),1);
    }
}