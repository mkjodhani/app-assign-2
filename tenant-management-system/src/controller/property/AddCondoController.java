package controller.property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.geography.Address;
import model.property.Condo;
import model.property.Property;
import services.property.PropertyService;

import javax.xml.soap.Text;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */
public class AddCondoController {
    private static PropertyService propertyService = PropertyService.getPropertyService();
    @FXML
    TextField aptNumberText;
    @FXML
    TextField numberOfBedroomsText;
    @FXML
    TextField numberOfBathroomsText;
    @FXML
    TextField squareFootageText;
    @FXML
    TextField rentText;
    @FXML
    TextField streetText;
    @FXML
    TextField cityText;
    @FXML
    ComboBox<String> provinceText;
    @FXML
    TextField postalCodeText;
    @FXML
    TextField streetAddressNumberText;

    @FXML
    Button addCondoButton;
    public void addCondo(ActionEvent actionEvent) {
        try{
            int aptNumber,numberOfBedrooms,numberOfBathrooms,streetAddressNumber;
            double squareFootage, rent;
            String street, city, province, postalCode;
            aptNumber = Integer.valueOf(aptNumberText.getText());
            numberOfBathrooms = Integer.valueOf(numberOfBathroomsText.getText());
            numberOfBedrooms = Integer.valueOf(numberOfBedroomsText.getText());
            streetAddressNumber = Integer.valueOf(streetAddressNumberText.getText());
            squareFootage = Double.valueOf(squareFootageText.getText());
            rent = Double.valueOf(rentText.getText());
            street =streetText.getText();
            city = cityText.getText();
            province = provinceText.getValue();
            postalCode = postalCodeText.getText();
            Address address = Address.generateAddress(street,city,province,postalCode,streetAddressNumber);
            Property property = new Condo(address,numberOfBedrooms,numberOfBathrooms,squareFootage,aptNumber,rent);
            property = propertyService.addProperty(Property.PROPERTY_TYPE.APARTMENT,property);
            Stage stage = (Stage) addCondoButton.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Property #"+property.getPropertyId() +" added successfully!");
            alert.setContentText(address.toString());
            alert.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Something went wrong!!");
            alert.setContentText(e.getMessage());
            clearInput();
            alert.showAndWait();
        }
    }
    private void clearInput(){
        aptNumberText.setText("");
        numberOfBathroomsText.setText("");
        numberOfBedroomsText.setText("");
        streetAddressNumberText.setText("");
        rentText.setText("");
        streetText.setText("");
        cityText.setText("");
        postalCodeText.setText("");
        provinceText.setValue("");
        squareFootageText.setText("");
    }
}
