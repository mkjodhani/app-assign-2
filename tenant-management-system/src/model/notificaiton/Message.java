package model.notificaiton;

import javafx.scene.control.Tab;
import view.cli.helper.Table;

import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Message {
    private Date timeStamp;
    private int userID;
    private String message;

    public Message(int userID, String message) {
        this.timeStamp = new Date();
        this.userID = userID;
        this.message = message;
    }

    public void show() {
        Table table = new Table();
        table.addRow(message,"", Table.POSITION.LEFT);
        table.addRow("",timeStamp.toLocaleString(), Table.POSITION.LEFT);
        table.show();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }
}
