package controller;

import model.person.Tenant;
import model.property.Property;
import services.users.TenantService;

import java.util.ArrayList;

public class TenantController {
    private static TenantService tenantService;
    private static TenantController tenantController;
    private TenantController(){
        tenantService = TenantService.getTenantService();
    }

    public static TenantController getTenantController() {
        if(tenantController == null){
            tenantController = new TenantController();
        }
        return tenantController;
    }

    public ArrayList<Tenant> getTenantsByRentPaid(boolean paid){
        return tenantService.getTenantsByRentPaid(paid);
    }
    public boolean payRent(int leaseID){
        return tenantService.payRent(leaseID);
    }
    public Tenant getTenantByID(int tenantID){
        return tenantService.getTenantById(tenantID);
    }

}
