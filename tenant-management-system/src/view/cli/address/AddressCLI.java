package view.cli.address;

import model.geography.Address;
import view.cli.helper.Input;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class AddressCLI {
    public static Address generateAddress(){
        String street, city, province, postalCode;
        int streetAddressNumber;
        street = Input.getString("Provide street name:");
        city = Input.getString("Provide city:");
        province = Input.getString("Provide province:");
        postalCode = Input.getString("Provide postal code:");
        streetAddressNumber = Input.getInteger("Provide street building number:");
        return Address.generateAddress(street,city,province,postalCode,streetAddressNumber);
    }
}
