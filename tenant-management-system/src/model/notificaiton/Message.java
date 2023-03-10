package model.notificaiton;

import java.util.Date;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Message {
    private Date timeStamp;
    private String message;

    public Message(Date timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
