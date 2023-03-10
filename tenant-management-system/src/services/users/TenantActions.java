package services.users;

import model.accounting.AccountingDTO;
import model.person.Tenant;

import java.util.Date;


/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class TenantActions {
    private AccountingDTO accountingDTO;
    private static TenantActions tenantActions;

    private TenantActions() {
        accountingDTO = AccountingDTO.getDTO();
    }

    public Tenant addTenant(String firstName, String lastName, Date dateOfBirth, String email){
        Tenant tenant = new Tenant(firstName,lastName,dateOfBirth,email);
        accountingDTO.addTenant(tenant);
        return tenant;
    };

    public static TenantActions getTenantActions() {
        if(tenantActions == null){
            tenantActions = new TenantActions();
        }
        return tenantActions;
    }
}
