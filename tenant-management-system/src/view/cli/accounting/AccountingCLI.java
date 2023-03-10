package view.cli.accounting;

import model.person.Tenant;
import model.property.House;
import model.property.Property;
import services.accounting.AccountingService;
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
    public AccountingCLI() {
        this.service = AccountingService.getAccountingService();
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
                "9. Payment details\n" +
                "10. Exit";
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
    private void showProperties(){
        Property.PROPERTY_TYPE propertyType;
        String propertyChoices = "Select Property Type:\n" +
                "1.Apartment\n" +
                "2.Condo\n" +
                "3.House\n" +
                "4.Exit";
        int choice = Input.getIntegerInRange(propertyChoices,1,4);
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
            default:
                return;
        }
        Collection<Property> properties = service.getPropertiesByType(propertyType);
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

    public void run(String[] args) {
        int choice = 0;
        while (choice != 10){
            choice = getChoiceFromMainMenu();
            switch (choice){
                case 1:
                    this.addProperty();
                    break;
                case 2:
                    this.addTenant();
                    break;
                case 3:
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
                    break;
                case 9:
                    break;
                case 10:
                    return;
            }
        }
    }
}
