package controller.tenant;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.person.Tenant;
import services.users.TenantService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author mkjodhani
 * @project
 * @since 15/03/23
 */
public class AddTenantController {
    TenantService tenantService = TenantService.getTenantService();
    @FXML
    TextField firstNameText;
    @FXML
    TextField lastNameText;
    @FXML
    TextField emailText;
    @FXML
    DatePicker dobObject;
    @FXML
    Button addTenantButton;

    public void addTenant(ActionEvent actionEvent) {
        try{
            Thread thread = new Thread(() ->{
                String firstName,lastName,email;
                Date dateOfBirth;
                firstName = firstNameText.getText();
                email = emailText.getText();
                lastName = lastNameText.getText();
                LocalDate localDate = dobObject.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                dateOfBirth = date;
                Tenant tenant = tenantService.addTenant(firstName,lastName,dateOfBirth,email);
                Platform.runLater(() ->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Tenant #"+tenant.getId() +" added successfully!");
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
            firstNameText.setText("");
            lastNameText.setText("");
            dobObject.setValue(LocalDate.now());
            emailText.setText("");
        });
    }
}
