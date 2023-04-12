package controller.property;

/**
 * @author mkjodhani
 * @project
 * @since 15/03/23
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.geography.Address;
import model.property.House;
import model.property.Property;
import services.property.PropertyService;


/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */
public class AddHouseController {
    private static PropertyService propertyService = PropertyService.getPropertyService();
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
    Button addHouseButton;
    public void addHouse(ActionEvent actionEvent) {
        try{
           Thread thread = new Thread(() ->{
               int numberOfBedrooms,numberOfBathrooms,streetAddressNumber;
               double squareFootage, rent;
               String street, city, province, postalCode;
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
               final Property[] property = {new House(address, numberOfBedrooms, numberOfBathrooms, squareFootage, rent)};
               Platform.runLater(() ->{
                   property[0] = propertyService.addProperty(Property.PROPERTY_TYPE.APARTMENT, property[0]);
                   Stage stage = (Stage) addHouseButton.getScene().getWindow();
                   stage.close();
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Success");
                   alert.setHeaderText("Property #"+ property[0].getPropertyId() +" added successfully!");
                   alert.setContentText(address.toString());
                   clearInput();
                   alert.showAndWait();
               });
           });
           thread.start();
        }catch (Exception e){
            Platform.runLater(() ->{
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Something went wrong!!");
                alert.setContentText(e.getMessage());
                clearInput();
                alert.showAndWait();
            });
        }
    }
    private void clearInput(){
        Platform.runLater(() ->{
            numberOfBathroomsText.setText("");
            numberOfBedroomsText.setText("");
            streetAddressNumberText.setText("");
            rentText.setText("");
            streetText.setText("");
            cityText.setText("");
            postalCodeText.setText("");
            provinceText.setValue("");
            squareFootageText.setText("");
        });
    }
}
