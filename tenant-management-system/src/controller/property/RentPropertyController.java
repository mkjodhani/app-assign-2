package controller.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.person.Tenant;
import model.property.Property;
import services.property.PropertyService;
import services.users.TenantService;


/**
 * @author mkjodhani
 * @project
 * @since 15/03/23
 */
public class RentPropertyController {
    private PropertyService propertyService = PropertyService.getPropertyService();
    private TenantService tenantService = TenantService.getTenantService();
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
    public void onFetchInfo(ActionEvent actionEvent) {
        try{
            int propertyId = Integer.valueOf(propertyIdText.getText());
            int tenantId = Integer.valueOf(propertyIdText.getText());
            Property property = propertyService.getPropertyByID(propertyId);
            Tenant tenant = tenantService.getTenantById(tenantId);
            if (property == null || tenant == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Input valid information.");
                alert.setContentText("Property #"+property.getPropertyId() +" not found!");
                alert.showAndWait();
            }
            else{
                aptNumberText.setText(String.valueOf(property.getPropertyId()));
                numberOfBedroomsText.setText(String.valueOf(property.getPropertyId()));
                numberOfBathroomsText.setText(String.valueOf(property.getPropertyId()));
                squareFootageText.setText(String.valueOf(property.getPropertyId()));
                rentText.setText(String.valueOf(property.getPropertyId()));
                streetAddressNumberText.setText(String.valueOf(property.getPropertyId()));
                cityText.setText(property.getAddressObject().getCity());
                provinceText.setText(property.getAddressObject().getCity());
                streetText.setText(property.getAddressObject().getCity());
                postalCodeText.setText(property.getAddressObject().getCity());
                firstNameText.setText(tenant.getFirstName());
                emailText.setText(tenant.getEmail());
                lastNameText.setText(tenant.getLastName());
            }
        }catch (Exception e){

        }
    }

    public void onRentProperty(ActionEvent actionEvent) {
    }
}
