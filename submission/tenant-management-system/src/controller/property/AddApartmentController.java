package controller.property;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.geography.Address;
import model.property.Apartment;
import model.property.Property;
import services.property.PropertyService;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */
public class AddApartmentController {
    String[] provinces = new String[]{
            "Alberta",
            "British Columbia",
            "Manitoba",
            "New Brunswick",
            "Newfoundland and Labrador",
            "Northwest Territories",
            "Nova Scotia",
            "Nunavut",
            "Ontario",
            "Prince Edward Island",
            "Quebec",
            "Saskatchewan",
            "Yukon"};
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
    Button addApartmentButton;
    public void initialize(){
        Platform.runLater(() ->{
            provinceText.getItems().setAll(provinces);
        });
    }
    public void addApartment(ActionEvent actionEvent) {
        Thread thread = new Thread(() ->{
            try{
                System.out.println(aptNumberText.getText());
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
                Platform.runLater(() ->{
                    Platform.runLater(() ->{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        Property property = new Apartment(address,numberOfBedrooms,numberOfBathrooms,squareFootage,aptNumber,rent);
                        property = propertyService.addProperty(Property.PROPERTY_TYPE.APARTMENT,property);    alert.setTitle("Success");
                        alert.setHeaderText("Property #"+property.getPropertyId() +" added successfully!");
                        alert.setContentText(address.toString());
                        clearInput();
                        alert.showAndWait();
                    });
                });
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
        });
        thread.start();
    }
    private void clearInput(){
        Platform.runLater(() ->{
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
        });
    }
}
