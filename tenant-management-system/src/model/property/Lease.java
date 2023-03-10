package model.property;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.person.Tenant;

import java.util.Date;


/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Lease implements Observable {
    private Tenant tenant;
    private Property property;
    Date startDate, endDate;
    double amount;

    public void terminateLease(){
//        property.
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
