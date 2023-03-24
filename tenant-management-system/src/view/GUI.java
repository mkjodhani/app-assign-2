package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.geography.Address;
import model.person.Tenant;
import model.property.House;
import model.property.Property;
import services.property.PropertyService;
import services.users.TenantService;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 15/03/23
 */
public class GUI extends Application implements Observer {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/screen/Home.fxml"));
        Scene scene = new Scene(root,700,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tenant Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        TenantService tenantService = TenantService.getTenantService();
        tenantService.addTenant("Mayur","Jodhani",new Date(),"mkjodhani133@gmail.com");
        PropertyService propertyService = PropertyService.getPropertyService();
        Address address = Address.generateAddress("Saint Marc","Montreal","Quebec","H3W 2N9",2000);
        Property house = new House(address,2,1,1200,1425);
        propertyService.addProperty(Property.PROPERTY_TYPE.CONDO,house);
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        stage.show();
    }
}
