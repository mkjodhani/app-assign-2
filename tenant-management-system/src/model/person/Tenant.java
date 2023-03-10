package model.person;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Tenant extends Person implements Observer {
    public Tenant(String firstName, String lastName, Date dateOfBirth, String email) {
        super(firstName, lastName, dateOfBirth, email);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
