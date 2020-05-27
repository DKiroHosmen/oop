package oop.base_entities;

public final class Vehicle {
    public static final Vehicle NO_VEHICLE = new Vehicle("", "", "", VehicleTypes.NONE);
    private String registrationNumber, vendor, model;
    VehicleTypes type;

    public Vehicle(VehicleTypes type) {
        this("", "", "", type);
    }

    public Vehicle(String registrationNumber, String vendor, String model, VehicleTypes type) {
        this.registrationNumber = registrationNumber;
        this.vendor = vendor;
        this.model = model;
        this.type = type;
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

    public VehicleTypes getType() {
        return type;
    }

    public void setType(VehicleTypes type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return vendor.hashCode() *
                model.hashCode() *
                type.hashCode() *
                registrationNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Vehicle object = (Vehicle) obj;
        return object.getType().equals(type) &&
                object.getModel().equals(model) &&
                object.getRegistrationNumber().equals(registrationNumber) &&
                object.getVendor().equals(vendor);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) regNumber: %s", vendor, model, type, registrationNumber);
    }
}
