package oop.model;

public class Space extends Throwable {
    private Vehicle vehicle;
    private Person person;


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isEmpty() {
        return vehicle.equals(Vehicle.NO_VEHICLE);
    }
}

