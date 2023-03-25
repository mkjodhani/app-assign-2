package controller.screen;

/**
 * @author mkjodhani
 * @version 2.0
 * @project Tenant Management System
 * @since 14/03/23
 */

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
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
import model.geography.Address;
import model.person.Tenant;
import model.property.House;
import model.property.Lease;
import model.property.Property;
import services.property.PropertyService;
import services.users.TenantService;

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
    }
    private void initializeTenantList(){
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
    }
    private void initializeLeaseList(){
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

            tableView.getColumns().add(leaseIdCol);
            tableView.getColumns().add(startDateCol);
            tableView.getColumns().add(activeCol);
            tableView.getColumns().add(endDateCol);

            Tenant tenant = new Tenant("Mayur","Jodhani",new Date(),"mkjodhani133@gmail.com");
            Address address1 = Address.generateAddress("Saint Marc","Montreal","Quebec","H3W 2N9",2000);
            Property house1 = new House(address1,2,1,1200,1425);
            Lease lease = new Lease(tenant,house1,11);

            ObservableList<Lease> people = FXCollections.observableArrayList(lease);
            tableView.setItems(people);
            if(leaseList.getChildren().size()>0){
                leaseList.getChildren().removeAll(leaseList.getChildren());
            }
            leaseList.getChildren().add(tableView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initialize(){
        MockData.getReference().addObserver(this);
        initializePropertyList();
        initializeTenantList();
        initializeLeaseList();
    }
    public void onRentPropertyClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/property/rent-property.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Rent Property");
        stage.setScene(new Scene(root, 700, 450));
        stage.show();
    }

    public void onAddPaymentClick(ActionEvent actionEvent) {
    }

    public void onChangePropertyStatusClick(ActionEvent actionEvent) {
    }

    public void onTerminateLeaseClick(ActionEvent actionEvent) {
    }

    public void onShowInterestedPropertyClick(ActionEvent actionEvent) {
    }

    public void onShowNotificationsClick(ActionEvent actionEvent) {
    }

    public void onAddTenantClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tenant/add-tenant.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Tenant");
        stage.setScene(new Scene(root, 700, 350));
        stage.setFullScreen(false);
        stage.setOnCloseRequest(e -> initializeTenantList());
        stage.show();
    }

    public void onAboutUsClick(ActionEvent actionEvent) {
    }

    public void onAddPropertyClick(ActionEvent actionEvent) throws IOException {
        String[] propertyTypes = new String[]{"Apartment","Condo","House"};
        ChoiceDialog<String> propertyTypeDialogBox = new ChoiceDialog<>(propertyTypes[0],propertyTypes);
        propertyTypeDialogBox.setTitle("Select Property Type");
        Optional<String> selectedProperty = propertyTypeDialogBox.showAndWait();
        if (selectedProperty.isPresent()){
            if (selectedProperty.get().equals(propertyTypes[0])){
                Parent root = FXMLLoader.load(getClass().getResource("/property/add-apartment.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Apartment");
                stage.setScene(new Scene(root, 700, 350));
                stage.show();
                stage.setOnCloseRequest(e -> initializePropertyList());
            }else if (selectedProperty.get().equals(propertyTypes[1])){
                Parent root = FXMLLoader.load(getClass().getResource("/property/add-condo.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Condo");
                stage.setScene(new Scene(root, 700, 350));
                stage.show();
                stage.setOnCloseRequest(e -> initializePropertyList());
            }
            else {
                Parent root = FXMLLoader.load(getClass().getResource("/property/add-house.fxml"));
                Stage stage = new Stage();
                stage.setTitle("House");
                stage.setScene(new Scene(root, 700, 350));
                stage.show();
                stage.setOnCloseRequest(e -> initializePropertyList());
            }
        }

    }
    public void onShowRentedProperty(ActionEvent actionEvent) throws IOException {
        Collection<Property> properties = new ArrayList<>();
        for(Property.PROPERTY_TYPE propertyType: Property.PROPERTY_TYPE.values()){
            System.out.println("Type: "+propertyType);
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
    }
    public void onShowVacantProperty(ActionEvent actionEvent) throws IOException {
        Collection<Property> properties = new ArrayList<>();
        for (Property property: propertyService.getAll()){
            if(property.getStatus() != Property.AVAILABILITY_TYPE.RENTED){
                properties.add(property);
            }
        }
        showPropertyList(properties,"Vacant Properties");
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
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UPDATETTETETTETETTETE");
        initialize();
    }
}