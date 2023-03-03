package model.person;

import java.time.LocalDate;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Admin extends Person{
    public Admin(String firstName, String lastName, LocalDate dateOfBirth, String mobileNumber) {
        super(firstName, lastName, dateOfBirth, mobileNumber);
    }
}
