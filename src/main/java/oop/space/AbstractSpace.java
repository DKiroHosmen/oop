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

    @Override
    public int hashCode() {
        return person.hashCode() *
                vehicle.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        AbstractSpace object = (AbstractSpace) obj;
        return object.getPerson().equals(person) &&
                object.getVehicle().equals(vehicle);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("Tenant: %s ТС: %s", person, vehicle);
    }
}
