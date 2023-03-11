package services.users;

import model.data.MockData;
import model.person.Tenant;
import model.property.Lease;

import java.util.ArrayList;


/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class TenantService {
    private MockData data;
    private static TenantService tenantService;
    public static TenantService getTenantService(){
        if (tenantService == null) {
            tenantService = new TenantService();
        }
        return tenantService;
    }
    public TenantService() {
        data = MockData.getReference();
    }

    public ArrayList<Tenant> getTenantsByRentPaid(boolean paid){
        ArrayList<Tenant> tenants = new ArrayList<>();
        for(Lease lease: data.getLeases().values()){
            if(lease.isRentPaidForCurrentMonth() == paid){
                tenants.add(lease.getTenant());
            }
        }
        return tenants;
    }

    public boolean payRent(int leaseID){
        Lease lease = this.data.getLeases().get(leaseID);
        if(lease == null){
            return false;
        }
        lease.payRent();
        return true;
    }
}
