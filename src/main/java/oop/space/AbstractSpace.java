package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;

public abstract class AbstractSpace implements Space {

    protected Person person;
    protected Vehicle vehicle;

    public AbstractSpace() {
        this(Person.NO_NAME, Vehicle.NO_VEHICLE);
    }

    public AbstractSpace(Person person) {
        this(person, Vehicle.NO_VEHICLE);
    }

    public AbstractSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean IsEmpty() {
        return vehicle.equals(Vehicle.NO_VEHICLE) || vehicle.getType().equals(VehicleTypes.NONE);
    }

}
