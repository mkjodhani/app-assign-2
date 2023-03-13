package view.cli.accounting;

import controller.AccountingController;
import controller.PropertyController;
import controller.TenantController;
import model.person.Tenant;
import model.property.Lease;
import model.property.Property;
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
    private AccountingController accountingController;
    private TenantController tenantController;
    private PropertyController propertyController;
    public AccountingCLI() {
        this.accountingController = AccountingController.getAccountingController();
        this.tenantController = TenantController.getTenantController();
        this.propertyController = PropertyController.getPropertyController();
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
                "9. Rent Payment\n" + // TODO
                "10. Change Property Status\n" +
                "11. List of tenant with unpaid rent\n" +
                "12. List of tenants with rent paid\n" +
                "13. Terminate Lease\n" +
                "14. Add Tenant Interest\n" +
                "15. Show notifications\n" +
                "16. Exit";
        return Input.getIntegerInRange(mainMenu,1,15);
    }

    private void addTenant(){
        String firstName, lastName, email;
        Date dateOfBirth;
        firstName = Input.getString("Enter the first name:");
        lastName = Input.getString("Enter the last name:");
        email = Input.getString("Enter the email:");
        dateOfBirth = Input.getDate("Please provide the date of birth(yyyy-MM-dd)");
        Tenant tenant = accountingController.addTenant(firstName,lastName,dateOfBirth,email);
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
        property = accountingController.addProperty(propertyType,property);
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
        Collection<Property> properties = accountingController.getPropertiesByType(getPropertyType());
        if(properties.size() == 0){
            System.out.println("No property found!");
        }
        else{
            for (Property property :properties){
                property.show();
            }
        }
    }
    private void showTenants(){
        Collection<Tenant> tenants = accountingController.getTenants();
        if(tenants.size() == 0){
            System.out.println("No tenants found!");
        }
        else{
            for (Tenant tenant :tenants){
                tenant.show();
            }
        }
    }
    private void showRentedUnits(){
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            System.out.println("Type: "+propertyType);
            Collection<Property> properties = propertyController.getPropertiesByStatus(propertyType, Property.AVAILABILITY_TYPE.RENTED);
            if(properties.size() == 0){
                System.out.println(String.format("No property found for %s type!",propertyType));
            }
            else{
                for (Property house : properties){
                    house.show();
                }
            }
        }
    }
    private void showNotRentedUnits(){
        Collection<Property> properties = propertyController.getProperties();
        int count = 0;
        for (Property property: properties){
            if(property.getStatus() != Property.AVAILABILITY_TYPE.RENTED){
                property.show();
                count++;
            }
        }
        if (count == 0){
            System.out.println("No property found!");
        }
    }

    private void rentUnit(){
        int tenantID = Input.getInteger("Enter Tenant ID");
        int propertyID = Input.getInteger("Enter Property ID");
        int months = Input.getInteger("Enter months");
        int result = accountingController.rentUnit(tenantID,propertyID,months);
        if (result <= 0){
            System.out.println("Something went wrong!\nPlease try again after some time!");
        }
        else {
            System.out.println("Operation performed successfully");
        }
    }

    public void showAllLeases(){
        Collection<Lease> leases = accountingController.getLeases();
        if(leases.size() == 0){
            System.out.println("No lease found!");
        }
        else{
            for (Lease lease :leases){
                lease.show();
            }
        }
    }
    public void showTenantsByRentPaymentStatus(boolean paid){
        Collection<Tenant> tenants = tenantController.getTenantsByRentPaid(paid);
        if (tenants.size() == 0){
            System.out.println("No tenants found for this option!!");
        }
        else{
            for (Tenant tenant: tenants){
                tenant.show();
            }
        }
    }
    private void payRent() {
        int leaseID = Input.getInteger("Enter Lease ID");
        boolean result = propertyController.payRent(leaseID);
        if(result){
            System.out.println("Operation performed successfully!!");
        }
        else{
            System.out.println("Something went wrong!!\nPlease provide valid information.");
        }
    }
    private void subscribeProperty() {
        int tenantID = Input.getInteger("Enter Tenant ID");
        int propertyID = Input.getInteger("Enter Property ID");
        boolean result = accountingController.subscribeProperty(tenantID,propertyID);
        if (result){
            System.out.println("Operation performed successfully");
        }
        else {
            System.out.println("Something went wrong!\nPlease try again after some time!");
        }
    }

    private void terminateLease() {
        int leaseID = Input.getInteger("Provide Lease ID:");
        boolean result = propertyController.terminateLease(leaseID);
        if (result){
            System.out.println("Operation performed successfully!!");
        }
        else {
            System.out.println("Something went wrong!!");
        }
    }

    private void changePropertyStatus() {
        int propertyID = Input.getInteger("Enter Property ID");
        Property.AVAILABILITY_TYPE availabilityType = getAvailabilityType();
        boolean result = propertyController.updatePropertyStatus(propertyID,availabilityType);
        if (result){
            System.out.println("Operation performed successfully!!");
        }
        else {
            System.out.println("Something went wrong!!");
        }

    }
    public void run(String[] args) {
        int choice = 0;
        while (choice != 16){
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
                    this.payRent();
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
                    this.terminateLease();
                    break;
                case 14:
                    this.subscribeProperty();
                    break;
                case 15:
                    this.showNotifications();
                    break;
                case 16:
                    return;
            }
        }
    }

    private void showNotifications() {
        int tenantId = Input.getInteger("Provide tenant ID:");
        Tenant tenant = tenantController.getTenantByID(tenantId);
        if(tenant == null){
            System.out.println("No tenant available for given ID.");
        }
        else{
            tenant.showMessages();
        }
    }
}
