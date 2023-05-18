package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.geography.Address;
import model.property.Condo;
import model.property.Property;
import services.accounting.AccountingService;
import services.property.PropertyService;
import services.users.TenantService;

import java.util.*;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */

public class GUI extends Application implements Observer {

    public static void importDummyData(){
        Thread thread = new Thread(() ->{
            AccountingService accountingService = AccountingService.getAccountingService();
            PropertyService propertyService = PropertyService.getPropertyService();
            TenantService tenantService = TenantService.getTenantService();
            Address address = Address.generateAddress("Saint Marc","Montreal","Quebec","H3W 1C5",2000);
            // ADD PROPERTY
            Property property1 = new Condo(address,2,1,1200,1905,1200);
            Property property2 = new Condo(address,5,3,1500,1405,2500);
            Property property3 = new Condo(address,3,7,2000,1105,2000);
            propertyService.addProperty(property1.getPropertyType(),property1);
            propertyService.addProperty(property2.getPropertyType(),property2);
            propertyService.addProperty(property3.getPropertyType(),property3);

            tenantService.addTenant("Mayur","Jodhani",new Date(),"mkjodhani@gmail.com");
            tenantService.addTenant("Snehee","Patel",new Date(),"snehee@gmail.com");
            tenantService.addTenant("Dharmil","Vaghasiya",new Date(),"dharmil@gmail.com");


            accountingService.subscribeProperty(2,1);
            int leaseID = accountingService.rentUnit(1,1,10);
            tenantService.payRent(leaseID);
        });
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
            stage = primaryStage;
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/screen/Home.fxml"));
            Scene scene = new Scene(root,700,700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tenant Management System");
            primaryStage.show();
    }

    public static void main(String[] args) {
        importDummyData();
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        Thread thread = new Thread(){
            public void run(){
                stage.show();
            }
        };
    }

    public static void showSuccessMessageBox(String title,String header,String message){
        Platform.runLater(() ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    public static void showErrorMessageBox(String title,String header,String message){
       Platform.runLater(() ->{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle(title);
           alert.setHeaderText(header);
           alert.setContentText(message);
           alert.showAndWait();
       });
    }

    public static Property.PROPERTY_TYPE selectPropertyType(){
        ChoiceDialog<Property.PROPERTY_TYPE> propertyTypeDialogBox = new ChoiceDialog<>(Property.PROPERTY_TYPE.values()[0], Property.PROPERTY_TYPE.values());
        propertyTypeDialogBox.setTitle("Select Property Type");
        Optional<Property.PROPERTY_TYPE> selectedOption = propertyTypeDialogBox.showAndWait();
        if (selectedOption.isPresent()){
            return selectedOption.get();
        }
        return null;
    }
    public static Property.AVAILABILITY_TYPE selectPropertyStatus(){
        ChoiceDialog<Property.AVAILABILITY_TYPE> propertyTypeDialogBox = new ChoiceDialog<>(Property.AVAILABILITY_TYPE.values()[0], Property.AVAILABILITY_TYPE.values());
        propertyTypeDialogBox.setTitle("Status");
        Optional<Property.AVAILABILITY_TYPE> selectedOption = propertyTypeDialogBox.showAndWait();
        if (selectedOption.isPresent()){
            return selectedOption.get();
        }
        return null;
    }

    public static int getIntegerValue(String title,String description){
        TextInputDialog integerDialogBox = new TextInputDialog();
        integerDialogBox.setTitle(title);
        integerDialogBox.setContentText(description);
        Optional<String> selectedOption = integerDialogBox.showAndWait();
        if(!selectedOption.isPresent()){
            return 0;
        }
        try {
            return Integer.valueOf(selectedOption.get());
        }
        catch (Exception e){
            showErrorMessageBox("Error!","Please provide valid information.","");
            return getIntegerValue(title,description);
        }
    }


}
