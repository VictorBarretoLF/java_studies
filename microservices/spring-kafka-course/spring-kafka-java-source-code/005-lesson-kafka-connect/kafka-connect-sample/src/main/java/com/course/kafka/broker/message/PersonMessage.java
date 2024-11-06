package com.course.kafka.broker.message;

import java.util.List;

public class PersonMessage {

    private int personId;
    private String fullName;
    private String email;
    private List<AddressMessage> addresses;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressMessage> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressMessage> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "PersonMessage [personId=" + personId + ", fullName=" + fullName + ", email=" + email + ", addresses="
                + addresses + "]";
    }

}
