package model.person;

import model.notificaiton.Message;
import model.property.Property;

import java.util.*;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Tenant extends Person implements Observer {
    private HashMap<Integer,Property> interestedProperties;
    private Stack<Message> messages;
    public Tenant(String firstName, String lastName, Date dateOfBirth, String email) {
        super(firstName, lastName, dateOfBirth, email);
        interestedProperties = new HashMap<>();
        messages = new Stack<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        Property property = (Property) arg;
        String messageString = String.format("Property #%d is now %s",property.getPropertyId(),property.getStatus());
        Message message = new Message(this.id,messageString);
        System.out.println("Notification received by Tenant #"+this.id+" :: "+messageString);
        messages.push(message);
        if(property.getStatus() != Property.AVAILABILITY_TYPE.RENTED ){
            interestedProperties.put(property.getPropertyId(),property);
        }
        else {
            interestedProperties.remove(property.getPropertyId());
        }
    }

    public HashMap<Integer, Property> getInterestedProperties() {
        return interestedProperties;
    }

    public Stack<Message> getMessages() {
        return messages;
    }

    public void showMessages(){
        if(messages.size() == 0){
            System.out.println("No notifications found!!");
            return;
        }
        for (Object message:messages.toArray()){
            ((Message)message).show();
        }
    }
}
