package controller.property;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.person.Tenant;
import model.property.Property;
import services.accounting.AccountingService;
import services.property.PropertyService;
import services.users.TenantService;

import java.util.Optional;


/**
 * @author mkjodhani
 * @project
 * @since 15/03/23
 */
public class RentPropertyController {
    private PropertyService propertyService = PropertyService.getPropertyService();
    private TenantService tenantService = TenantService.getTenantService();
    private AccountingService accountingService = AccountingService.getAccountingService();
    @FXML
    Button fetchInfoButton;
    @FXML
    Button rentPropertyButton;
    @FXML
    TextField propertyIdText;
    @FXML
    TextField  tenantIdText;
    @FXML
    TextField aptNumberText;
    @FXML
    TextField  numberOfBedroomsText;
    @FXML
    TextField  numberOfBathroomsText;
    @FXML
    TextField  squareFootageText;
    @FXML
    TextField  rentText;
    @FXML
    TextField  streetAddressNumberText;
    @FXML
    TextField  cityText;
    @FXML
    TextField  provinceText;
    @FXML
    TextField  streetText;
    @FXML
    TextField  postalCodeText;
    @FXML
    TextField  firstNameText;
    @FXML
    TextField  emailText;
    @FXML
    TextField  lastNameText;

    public void clearInput(){
        aptNumberText.setText("");
        numberOfBedroomsText.setText("");
        numberOfBathroomsText.setText("");
        squareFootageText.setText("");
        rentText.setText("");
        streetAddressNumberText.setText("");
        cityText.setText("");
        provinceText.setText("");
        streetText.setText("");
        postalCodeText.setText("");
        firstNameText.setText("");
        emailText.setText("");
        lastNameText.setText("");
    }
    public void onFetchInfo(ActionEvent actionEvent) {
        try{
            int propertyId = Integer.valueOf(propertyIdText.getText());
            int tenantId = Integer.valueOf(tenantIdText .getText());
            Property property = propertyService.getPropertyByID(propertyId);
            Tenant tenant = tenantService.getTenantById(tenantId);
            if (property == null || tenant == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Input valid information.");
                clearInput();
                alert.showAndWait();
            }
            else{
                aptNumberText.setText(String.valueOf(property.getPropertyId()));
                numberOfBedroomsText.setText(String.valueOf(property.getNumberOfBedrooms()));
                numberOfBathroomsText.setText(String.valueOf(property.getNumberOfBathrooms()));
                squareFootageText.setText(String.valueOf(property.getSquareFootage()));
                rentText.setText(String.valueOf(property.getRent()));
                streetAddressNumberText.setText(String.valueOf(property.getAddressObject().getStreetAddressNumber()));
                cityText.setText(property.getAddressObject().getCity());
                provinceText.setText(property.getAddressObject().getProvince());
                streetText.setText(property.getAddressObject().getStreet());
                postalCodeText.setText(property.getAddressObject().getPostalCode());
                firstNameText.setText(tenant.getFirstName());
                emailText.setText(tenant.getEmail());
                lastNameText.setText(tenant.getLastName());
            }
        }catch (Exception e){
            clearInput();
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Input valid information.");
            alert.showAndWait();
        }
    }

    public void onRentProperty(ActionEvent actionEvent) {
        try{
            int propertyId = Integer.valueOf(propertyIdText.getText());
            int tenantId = Integer.valueOf(propertyIdText.getText());
            Property property = propertyService.getPropertyByID(propertyId);
            Tenant tenant = tenantService.getTenantById(tenantId);
            if (property == null || tenant == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Input valid information.");
                clearInput();
                alert.showAndWait();
            }
            else{
                TextInputDialog dialog = new TextInputDialog("0");
                // setHeaderText
                dialog.setHeaderText("Enter months");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(value -> {
                    int months = Integer.valueOf(value);
                    accountingService.rentUnit(tenant.getId(),property.getPropertyId(),months);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Lease signed!!");
                    clearInput();
                    alert.showAndWait();
                });

            }
        }catch (Exception e){
                clearInput();
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Input valid information.");
                alert.showAndWait();
        }
    }
}
