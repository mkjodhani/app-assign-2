package controller.tenant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.notificaiton.Message;
import model.person.Tenant;
import services.users.TenantService;

import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 25/03/23
 */
public class Notifications implements Observer {
    Tenant tenant;
    @FXML
    AnchorPane anchorPane;

    private void loadData(){
        TableView tableView = new TableView();
        TableColumn<Message, String> timeStampCol = new TableColumn<>("Time");
        timeStampCol.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

        TableColumn<Message, String> messageCol = new TableColumn<>("Message");
        messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));

        tableView.getColumns().add(timeStampCol);
        tableView.getColumns().add(messageCol);
        tableView.setPlaceholder(new Label("No notifications found!"));
        ObservableList<Message> people = FXCollections.observableArrayList(tenant.getMessages());
        tableView.setItems(people);
        if(anchorPane.getChildren().size()>0){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
        }
        anchorPane.getChildren().add(tableView);
    }
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
        tenant.addObserver(this);
        loadData();
    }
    public void setTenantID(int tenantIDValue){
        System.out.println("setTenantID");
    }
    @Override
    public void update(Observable o, Object arg) {
        loadData();
    }
}
