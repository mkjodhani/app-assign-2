package model.person;

import view.cli.helper.Table;

import java.util.Date;
import java.util.Observer;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public abstract class Person implements Observer {

    private static int totalPerson = 0;
    protected int id;
    protected String firstName, lastName;
    protected Date dateOfBirth;
    protected String email;

    public Person(String firstName, String lastName, Date dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.id = ++totalPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void show() {
        Table table = new Table();
        table.addRow("Person","", Table.POSITION.LEFT);
        table.addRow("First Name", firstName, Table.POSITION.LEFT);
        table.addRow("Last Name",lastName, Table.POSITION.LEFT);
        table.addRow("Date Of Birth",dateOfBirth.toString(), Table.POSITION.LEFT);
        table.addRow("Email",email, Table.POSITION.LEFT);
        table.show();
    }
}
