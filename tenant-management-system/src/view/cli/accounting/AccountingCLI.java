package view.cli.accounting;

import model.person.Tenant;
import model.property.Lease;
import model.property.Property;
import services.property.PropertyService;
import services.accounting.AccountingService;
import services.users.TenantService;
import view.cli.helper.Input;
import view.cli.property.PropertyCLI;

import java.util.Collection;
import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 06/03/23
 */
public class AccountingCLI{
    private AccountingService service;
    private TenantService tenantService;

    private PropertyService propertyService;
    public AccountingCLI() {
        this.service = AccountingService.getAccountingService();
        this.tenantService = new TenantService();
        this.propertyService = new PropertyService();
    }
    public static int getChoiceFromMainMenu() {
        String mainMenu = "1. Add a property\n" +
                "2. Add a tenant\n" +
                "3. Rent a unit\n" +
                "4. Display properties\n" +
                "5. Display tenants\n" +
                "6. Display rented units\n" +
                "7. Display vacant units\n" +
                "8. Display all leases\n" +
                "9. Payment details\n" + // TODO
                "10. Change Property Status\n" +
                "11. List of tenant with unpaid rent\n" +
                "12. List of tenants with rent paid\n" +
//                terminate lease
                // add tenant interest
                "13. Exit";
        return Input.getIntegerInRange(mainMenu,1,10);
    }

    private void addTenant(){
        String firstName, lastName, email;
        Date dateOfBirth;
        firstName = Input.getString("Enter the first name:");
        lastName = Input.getString("Enter the last name:");
        email = Input.getString("Enter the email:");
        dateOfBirth = Input.getDate("Please provide the date of birth");
        Tenant tenant = service.addTenant(firstName,lastName,dateOfBirth,email);
        tenant.show();
    }
    private void addProperty(){
        Property.PROPERTY_TYPE propertyType;
        String propertyChoices = "Select Property Type:\n" +
                "1.Apartment\n" +
                "2.Condo\n" +
                "3.House\n" +
                "4.Exit";
        int choice = Input.getIntegerInRange(propertyChoices,1,4);
        Property property;
        switch (choice){
            case 1:
                propertyType = Property.PROPERTY_TYPE.APARTMENT;
                property = PropertyCLI.genearteApartment();
                break;
            case 2:
                propertyType = Property.PROPERTY_TYPE.CONDO;
                property = PropertyCLI.genearteCondo();
                break;
            case 3:
                propertyType = Property.PROPERTY_TYPE.HOUSE;
                property = PropertyCLI.genearteHouse();
                break;
            default:
                return;
        }
        property = service.addProperty(propertyType,property);
        property.show();
    }
    private Property.PROPERTY_TYPE getPropertyType(){
        Property.PROPERTY_TYPE propertyType = Property.PROPERTY_TYPE.CONDO;
        String propertyChoices = "Select Property Type:\n" +
                "1.Apartment\n" +
                "2.Condo\n" +
                "3.House";
        int choice = Input.getIntegerInRange(propertyChoices,1,3);
        switch (choice){
            case 1:
                propertyType = Property.PROPERTY_TYPE.APARTMENT;
                break;
            case 2:
                propertyType = Property.PROPERTY_TYPE.CONDO;
                break;
            case 3:
                propertyType = Property.PROPERTY_TYPE.HOUSE;
                break;
        }
        return propertyType;
    }
    private Property.AVAILABILITY_TYPE getAvailabilityType(){
        Property.AVAILABILITY_TYPE availabilityType = Property.AVAILABILITY_TYPE.RENTED;
        String propertyChoices = "Select Type:\n" +
                "1. Rented\n" +
                "2. Ready to be rented\n" +
                "3. Ready to be renovated";
        int choice = Input.getIntegerInRange(propertyChoices,1,3);
        switch (choice){
            case 1:
                availabilityType = Property.AVAILABILITY_TYPE.RENTED;
                break;
            case 2:
                availabilityType = Property.AVAILABILITY_TYPE.READY_TO_BE_RENTED;
                break;
            case 3:
                availabilityType = Property.AVAILABILITY_TYPE.READY_TO_BE_RENOVATED;
                break;
        }
        return availabilityType;
    }
    private void showProperties(){
        Collection<Property> properties = service.getPropertiesByType(getPropertyType());
        for (Property property :properties){
            property.show();
        }
    }
    private void showTenants(){
        Collection<Tenant> tenants = service.getTenants();
        for (Tenant tenant :tenants){
            tenant.show();
        }
    }
    private void showRentedUnits(){
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            System.out.println("Type: "+propertyType);
            for (Property.AVAILABILITY_TYPE availabilityType: Property.AVAILABILITY_TYPE.values()){
                if(availabilityType == Property.AVAILABILITY_TYPE.RENTED){
                    Collection<Property> houses = service.getPropertiesByStatus(Property.PROPERTY_TYPE.HOUSE, Property.AVAILABILITY_TYPE.RENTED);
                    for (Property house : houses){
                        house.show();
                    }
                }
            }
        }
    }
    private void showNotRentedUnits(){
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            System.out.println("Type: "+propertyType);
            for (Property.AVAILABILITY_TYPE availabilityType: Property.AVAILABILITY_TYPE.values()){
                if(availabilityType != Property.AVAILABILITY_TYPE.RENTED){
                    Collection<Property> houses = service.getPropertiesByStatus(Property.PROPERTY_TYPE.HOUSE, Property.AVAILABILITY_TYPE.RENTED);
                    for (Property house : houses){
                        house.show();
                    }
                }
            }
        }
    }

    private void rentUnit(){
        int tenantID = Input.getInteger("Enter Tenant ID");
        int propertyID = Input.getInteger("Enter Property ID");
        int result = service.rentUnit(tenantID,propertyID);
        if (result <= 0){
            System.out.println("Operation performed successfully");
        }
        else {
            System.out.println("Something went wrong!\nPlease try again after some time!");
        }
    }

    public void showAllLeases(){
        Collection<Lease> leases = service.getLeases();
        for (Lease lease :leases){
            lease.show();
        }
    }
    public void showTenantsByRentPaymentStatus(boolean paid){
        Collection<Tenant> tenants = tenantService.getTenantsByRentPaid(paid);
        for (Tenant tenant: tenants){
            tenant.show();
        }
    }


    public void run(String[] args) {
        int choice = 0;
        while (choice != 11){
            choice = getChoiceFromMainMenu();
            switch (choice){
                case 1:
                    this.addProperty();
                    break;
                case 2:
                    this.addTenant();
                    break;
                case 3:
                    this.rentUnit();
                    break;
                case 4:
                    this.showProperties();
                    break;
                case 5:
                    this.showTenants();
                    break;
                case 6:
                    this.showRentedUnits();
                    break;
                case 7:
                    this.showNotRentedUnits();
                    break;
                case 8:
                    this.showAllLeases();
                    break;
                case 9:
                    break;
                case 10:
                    this.changePropertyStatus();
                    break;
                case 11:
                    this.showTenantsByRentPaymentStatus(false);
                    break;
                case 12:
                    this.showTenantsByRentPaymentStatus(true);
                    break;
                case 13:
                    return;
            }
        }
    }

    private void changePropertyStatus() {
        int propertyID = Input.getInteger("Enter Property ID");
        Property.AVAILABILITY_TYPE availabilityType = getAvailabilityType();
        boolean result = propertyService.updatePropertyStatus(propertyID,availabilityType);
        if (result){
            System.out.println("Operation performed successfully!!");
        }
        else {
            System.out.println("Something went wrong!!");
        }

    }
}
