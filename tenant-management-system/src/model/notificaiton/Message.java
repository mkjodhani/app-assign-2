package model.notificaiton;

import java.time.LocalDate;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Message {
    private LocalDate timeStamp;
    private String message;

    public Message(LocalDate timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
