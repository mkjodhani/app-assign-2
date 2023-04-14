package controller.screen;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 14/03/23
 */

import controller.tenant.NotificationsController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.data.MockData;
import model.person.Tenant;
import model.property.Lease;
import model.property.Property;
import services.property.PropertyService;
import services.users.TenantService;
import view.GUI;

import java.io.IOException;
import java.util.*;

public class HomeController implements Observer {

    private PropertyService propertyService = PropertyService.getPropertyService();
    private TenantService tenantService = TenantService.getTenantService();
    @FXML
    private VBox propertyList;
    @FXML
    private VBox tenantList;
    @FXML
    private VBox leaseList;
    private void initializePropertyList(){
        Platform.runLater(() ->{
            try{
                TableView tableView = new TableView();

                TableColumn<Property, String> propertyIdCol = new TableColumn<>("Property ID");
                propertyIdCol.setCellValueFactory(new PropertyValueFactory<>("propertyId"));

                TableColumn<Property, String> numberOfBedroomsCol = new TableColumn<>("#Bedrooms");
                numberOfBedroomsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfBedrooms"));

                TableColumn<Property, String> numberOfBathroomsCol = new TableColumn<>("#Bathrooms");
                numberOfBathroomsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfBathrooms"));

                TableColumn<Property, String> squareFootageCol = new TableColumn<>("Square Footage");
                squareFootageCol.setCellValueFactory(new PropertyValueFactory<>("squareFootage"));

                TableColumn<Property, String> propertyTypeCol = new TableColumn<>("Property Type");
                propertyTypeCol.setCellValueFactory(new PropertyValueFactory<>("propertyType"));

                TableColumn<Property, String> statusCol = new TableColumn<>("Status");
                statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

                TableColumn<Property, String> rentCol = new TableColumn<>("Rent");
                rentCol.setCellValueFactory(new PropertyValueFactory<>("rent"));

                TableColumn<Property, String> addressCol = new TableColumn<>("Address");
                addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));


                tableView.getColumns().add(propertyIdCol);
                tableView.getColumns().add(numberOfBathroomsCol);
                tableView.getColumns().add(numberOfBedroomsCol);
                tableView.getColumns().add(squareFootageCol);
                tableView.getColumns().add(rentCol);
                tableView.getColumns().add(statusCol);
                tableView.getColumns().add(addressCol);

                ObservableList<Property> people = FXCollections.observableArrayList(propertyService.getAll());
                tableView.setItems(people);
                if(propertyList.getChildren().size()>0){
                    propertyList.getChildren().removeAll(propertyList.getChildren());
                }
                propertyList.getChildren().add(tableView);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    private void initializeTenantList(){
        Platform.runLater(() ->{
            try{
                TableView tableView = new TableView();
                TableColumn<Tenant, String> idCol = new TableColumn<>("Tenant ID");
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Property, String> firstNameCol = new TableColumn<>("First Name");
                firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                TableColumn<Property, String> lastNameCol = new TableColumn<>("Last Name");
                lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

                TableColumn<Property, String> dateOfBirthCol = new TableColumn<>("Date Of Birth");
                dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

                TableColumn<Property, String> emailCol = new TableColumn<>("Email");
                emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

                tableView.getColumns().add(idCol);
                tableView.getColumns().add(firstNameCol);
                tableView.getColumns().add(lastNameCol);
                tableView.getColumns().add(dateOfBirthCol);
                tableView.getColumns().add(emailCol);

                ObservableList<Tenant> people = FXCollections.observableArrayList(tenantService.getAll());
                tableView.setItems(people);
                if(tenantList.getChildren().size()>0){
                    tenantList.getChildren().removeAll(tenantList.getChildren());
                }
                tenantList.getChildren().add(tableView);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    private void initializeLeaseList(){
        Platform.runLater(() ->{
            try{
                TableView tableView = new TableView();

                TableColumn<Lease, String> leaseIdCol = new TableColumn<>("Lease ID");
                leaseIdCol.setCellValueFactory(new PropertyValueFactory<>("leaseId"));

                TableColumn<Lease, String> activeCol = new TableColumn<>("Active");
                activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

                TableColumn<Lease, String> startDateCol = new TableColumn<>("Start Date");
                startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

                TableColumn<Lease, String> endDateCol = new TableColumn<>("End Date");
                endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

                TableColumn<Lease, String> lastTransactionCol = new TableColumn<>("Last Payment");
                lastTransactionCol.setCellValueFactory(cellData -> {
                    String month = "";
                    if (cellData.getValue().getLastTransaction() != null){
                        month = cellData.getValue().getLastTransaction().getMonthYear();
                    }
                    return new SimpleStringProperty(month);
                });

                tableView.getColumns().add(leaseIdCol);
                tableView.getColumns().add(startDateCol);
                tableView.getColumns().add(endDateCol);
                tableView.getColumns().add(activeCol);
                tableView.getColumns().add(lastTransactionCol);

                ObservableList<Lease> leases = FXCollections.observableArrayList(propertyService.getLeases());
                tableView.setItems(leases);
                if(leaseList.getChildren().size()>0){
                    leaseList.getChildren().removeAll(leaseList.getChildren());
                }
                leaseList.getChildren().add(tableView);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    public void initialize(){
        MockData.getReference().addObserver(this);
        initializePropertyList();
        initializeTenantList();
        initializeLeaseList();
    }

    @Override
    public void update(Observable o, Object arg) {
        initialize();
    }
    public void onShowInterestedPropertyClick(ActionEvent actionEvent) {
        Thread thread = new Thread(() ->{
            int tenantID =  GUI.getIntegerValue("Provide Tenant ID","Tenant ID");
            Tenant tenant = tenantService.getTenantById(tenantID);
            if (tenant == null){
                GUI.showErrorMessageBox("Error!","No tenant found!","");
                return;
            }
            int propertyID =  GUI.getIntegerValue("Provide Property ID","Property ID");
            Property property = propertyService.getPropertyByID(propertyID);
            if (property == null){
                GUI.showErrorMessageBox("Error!","No property found!","");
            }
            property.addObserver(tenant);
            GUI.showSuccessMessageBox("Success!","You have registered for the provided property.","You will get notification if the property status is updated.");
        });
        thread.start();
    }
    public void onShowNotificationsClick(ActionEvent actionEvent) throws IOException {
        Thread thread = new Thread(() ->{
            try{
                int tenantID = GUI.getIntegerValue("Tenant ID","Provide valid Tenant ID");
                Tenant tenant = tenantService.getTenantById(tenantID);
                if (tenant == null){
                    GUI.showErrorMessageBox("Error!","No tenant found!","");
                    return;
                }
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tenant/notifications.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                NotificationsController notificationsController = fxmlLoader.getController();
                notificationsController.setTenant(tenant);
                stage.setTitle("Notifications for "+tenant.getFirstName()+ " " + tenant.getLastName());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public void onChangePropertyStatusClick(ActionEvent actionEvent) {
        Thread thread = new Thread(() ->{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Provide Property ID");
            dialog.setContentText("Property ID");
            Optional<String> propertyID = dialog.showAndWait();
            if (!propertyID.isPresent()){
                return;
            }
            try{
                int propertyIDValue = Integer.valueOf(propertyID.get());
                Property property = propertyService.getPropertyByID(propertyIDValue);
                if (property != null){
                    Property.AVAILABILITY_TYPE selectedType = GUI.selectPropertyStatus();
                    if (selectedType != null){
                        boolean result = propertyService.updatePropertyStatus(propertyIDValue,selectedType);
                        if (result){
                            GUI.showSuccessMessageBox("Success!","Lease terminated  successfully!","Property status has been updated.");
                        }
                        else {
                            GUI.showErrorMessageBox("Error!","Something went wrong!","");
                        }
                    }
                }
            }catch (Exception e){
                GUI.showSuccessMessageBox("Error!","Something went wrong!",e.getMessage());
            }
        });
        thread.start();
    }
    public void onRentPropertyClick(ActionEvent actionEvent) throws IOException {
        Thread thread = new Thread(() ->{
            try {
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/property/rent-property.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Rent Property");
                stage.setScene(new Scene(root, 700, 450));
                stage.show();} catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
    public void onTerminateLeaseClick(ActionEvent actionEvent) {
        Thread thread = new Thread(() ->{
            try{
                int leaseIDValue =  GUI.getIntegerValue("Provide Lease ID","Lease ID");
                if (leaseIDValue <=0){
                    return;
                }
                boolean result = propertyService.terminateLease(leaseIDValue);
                if (result){
                    GUI.showSuccessMessageBox("Success!","Lease terminated  successfully!","Property status has been updated.");
                }else {
                    GUI.showErrorMessageBox("Error!","Something went wrong!","");
                }
            }catch (Exception e){
                GUI.showSuccessMessageBox("Error!","Something went wrong!",e.getMessage());
            }
        });
        thread.start();
    }
    public void onAddTenantClick(ActionEvent actionEvent) throws IOException {
        Thread thread = new Thread(() ->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/tenant/add-tenant.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Tenant");
                stage.setScene(new Scene(root, 700, 350));
                stage.setFullScreen(false);
                stage.setOnCloseRequest(e -> initializeTenantList());
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }
    public void onAddPropertyClick(ActionEvent actionEvent) throws IOException {
        Thread thread = new Thread(() ->{
            String[] propertyTypes = new String[]{"Apartment", "Condo", "House"};
            ChoiceDialog<String> propertyTypeDialogBox = new ChoiceDialog<>(propertyTypes[0], propertyTypes);
            propertyTypeDialogBox.setTitle("Select Property Type");
            Optional<String> selectedProperty = propertyTypeDialogBox.showAndWait();
            if (selectedProperty.isPresent()) {
                if (selectedProperty.get().equals(propertyTypes[0])) {
                    Platform.runLater(() ->{
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/property/add-apartment.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Apartment");
                            stage.setScene(new Scene(root, 700, 350));
                            stage.show();
                            stage.setOnCloseRequest(e -> {
                                initializePropertyList();
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else if (selectedProperty.get().equals(propertyTypes[1])) {
                   Platform.runLater(() ->{
                       Parent root = null;
                       try {
                           root = FXMLLoader.load(getClass().getResource("/property/add-condo.fxml"));
                           Stage stage = new Stage();
                           stage.setTitle("Condo");
                           stage.setScene(new Scene(root, 700, 350));
                           stage.show();
                           stage.setOnCloseRequest(e -> {
                               initializePropertyList();
                           });
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   });
                } else {
                    Thread thread1 = new Thread(() ->{
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/property/add-house.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("House");
                            stage.setScene(new Scene(root, 700, 350));
                            stage.show();
                            stage.setOnCloseRequest(e -> {
                                initializePropertyList();
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    thread1.start();
                }
            }
        });
        thread.start();
    }
    public void onShowRentedProperty(ActionEvent actionEvent){
       Platform.runLater(() ->{
           Collection<Property> properties = new ArrayList<>();
           for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
               Collection<Property> propertyCollection = propertyService.getPropertiesByStatus(propertyType, Property.AVAILABILITY_TYPE.RENTED);
               if(propertyCollection.size() == 0){
                   System.out.println(String.format("No property found for %s type!",propertyType));
               }
               else{
                   for (Property house : propertyCollection){
                       properties.add(house);
                   }
               }
           }
           showPropertyList(properties,"Rented Properties");
       });
    }
    public void onShowVacantProperty(ActionEvent actionEvent){
        Platform.runLater(() ->{
            Collection<Property> properties = new ArrayList<>();
            for (Property property: propertyService.getAll()){
                if(property.getStatus() != Property.AVAILABILITY_TYPE.RENTED){
                    properties.add(property);
                }
            }
            showPropertyList(properties,"Vacant Properties");
        });
    }
    private void showPropertyList(Collection<Property> properties,String title){
        TableView tableView = new TableView();

        TableColumn<Property, String> propertyIdCol = new TableColumn<>("Property ID");
        propertyIdCol.setCellValueFactory(new PropertyValueFactory<>("propertyId"));

        TableColumn<Property, String> numberOfBedroomsCol = new TableColumn<>("#Bedrooms");
        numberOfBedroomsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfBedrooms"));

        TableColumn<Property, String> numberOfBathroomsCol = new TableColumn<>("#Bathrooms");
        numberOfBathroomsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfBathrooms"));

        TableColumn<Property, String> squareFootageCol = new TableColumn<>("Square Footage");
        squareFootageCol.setCellValueFactory(new PropertyValueFactory<>("squareFootage"));

        TableColumn<Property, String> propertyTypeCol = new TableColumn<>("Property Type");
        propertyTypeCol.setCellValueFactory(new PropertyValueFactory<>("propertyType"));

        TableColumn<Property, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Property, String> rentCol = new TableColumn<>("Rent");
        rentCol.setCellValueFactory(new PropertyValueFactory<>("rent"));

        TableColumn<Property, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableView.setPlaceholder(new Label("No property found!"));

        tableView.getColumns().add(propertyIdCol);
        tableView.getColumns().add(numberOfBathroomsCol);
        tableView.getColumns().add(numberOfBedroomsCol);
        tableView.getColumns().add(squareFootageCol);
        tableView.getColumns().add(rentCol);
        tableView.getColumns().add(statusCol);
        tableView.getColumns().add(addressCol);

        ObservableList<Property> people = FXCollections.observableArrayList(properties);
        tableView.setItems(people);
        Stage stage = new Stage();
        Scene scene = new Scene(tableView);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    public void showTenantsWithoutPaidRent(){
        Collection<Tenant> tenants = tenantService.getTenantsByRentPaid(false);
        showTenantList(tenants,"Tenants without paid rent");
    }
    public void showTenantsWithPaidRent(){
        Collection<Tenant> tenants = tenantService.getTenantsByRentPaid(true);
        showTenantList(tenants,"Tenants with paid rent");
    }
    private void showTenantList(Collection<Tenant> tenants,String title){
        Platform.runLater(() ->{
            TableView tableView = new TableView();
            TableColumn<Tenant, String> idCol = new TableColumn<>("Tenant ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Property, String> firstNameCol = new TableColumn<>("First Name");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Property, String> lastNameCol = new TableColumn<>("Last Name");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Property, String> dateOfBirthCol = new TableColumn<>("Date Of Birth");
            dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

            TableColumn<Property, String> emailCol = new TableColumn<>("Email");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

            tableView.getColumns().add(idCol);
            tableView.getColumns().add(firstNameCol);
            tableView.getColumns().add(lastNameCol);
            tableView.getColumns().add(dateOfBirthCol);
            tableView.getColumns().add(emailCol);
            tableView.setItems(FXCollections.observableArrayList(tenants));
            tableView.setPlaceholder(new Label("No tenants found!"));
            Stage stage = new Stage();
            Scene scene = new Scene(tableView);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        });
    }
    public void payRent(ActionEvent actionEvent) {
        Platform.runLater(() ->{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Provide Lease ID");
            dialog.setContentText("Lease ID");
            Optional<String> leaseID = dialog.showAndWait();

            if (!leaseID.isPresent()){
                return;
            }
            try{
                int leaseIDValue = Integer.valueOf(leaseID.get());
                boolean result = propertyService.payRent(leaseIDValue);
                if (result){
                    GUI.showSuccessMessageBox("Success!","Transaction recorded successfully!","Rent has been paid successfully!");
                }else {
                    GUI.showErrorMessageBox("Error!","Something went wrong!","");
                }
            }catch (Exception e){
                GUI.showSuccessMessageBox("Error!","Something went wrong!",e.getMessage());
            }
        });
    }
}