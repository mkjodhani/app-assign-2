package model.geography;

/**
 * @author mkjodhani
 * @project Tenant Management System
 * @since 02/03/23
 */
public class Address {
    private String street, city, province, postalCode;
    private int streetAddressNumber;

    private Address(String street, String city, String province, String postalCode, int streetAddressNumber) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.streetAddressNumber = streetAddressNumber;
    }
    public static Address generateAddress(String street, String city, String province, String postalCode, int streetAddressNumber) {
//        TODO validate the address
        return new Address(street,city,province,postalCode,streetAddressNumber);
    }

    @Override
    public String toString() {
        return streetAddressNumber + ", "  + street.toLowerCase() + ", " + city.toLowerCase() + ", " + province + ", " + postalCode.toUpperCase();
    }
}
