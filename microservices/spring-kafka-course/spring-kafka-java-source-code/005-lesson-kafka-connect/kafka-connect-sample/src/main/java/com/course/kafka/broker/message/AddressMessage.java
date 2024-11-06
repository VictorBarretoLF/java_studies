package com.course.kafka.broker.message;

public class AddressMessage {

    private int addressId;
    private String address;
    private String city;
    private String postalCode;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "AddressMessage [addressId=" + addressId + ", address=" + address + ", city=" + city + ", postalCode="
                + postalCode + "]";
    }

}
