package oop.base_entities;

public class Vehicle {
    private String registrationNumber, vendor, model;

    public Vehicle() {
        this("", "", "");
    }

    public Vehicle(String registrationNumber, String vendor, String model) {
        this.registrationNumber = registrationNumber;
        this.vendor = vendor;
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
