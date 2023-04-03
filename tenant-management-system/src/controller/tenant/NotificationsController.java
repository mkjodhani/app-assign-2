package controller.tenant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.notificaiton.Message;
import model.person.Tenant;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @project
 * @since 25/03/23
 */
public class NotificationsController implements Observer {
    Tenant tenant;
    @FXML
    AnchorPane anchorPane;

    private void loadData(){
        VBox list = new VBox(10);

        for (Message message:tenant.getMessages()){
            VBox notification = new VBox(5);
            notification.setPrefWidth(400);
            notification.getStyleClass().add("color-palette");
            notification.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            Label messageLabel = new Label(message.getMessage());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
            Label timeStamp = new Label(sdf.format(message.getTimeStamp()));
            notification.getChildren().add(messageLabel);
            notification.setFillWidth(true);
            timeStamp.setAlignment(Pos.BASELINE_RIGHT);
            VBox notificationBox = new VBox();
            notificationBox.setAlignment(Pos.TOP_RIGHT);
            notificationBox.getChildren().add(timeStamp);
            notification.getChildren().add(notificationBox);

            list.getChildren().add(notification);
        }
        if(anchorPane.getChildren().size()>0){
            anchorPane.getChildren().removeAll(anchorPane.getChildren());
        }
        anchorPane.getChildren().add(list);
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
