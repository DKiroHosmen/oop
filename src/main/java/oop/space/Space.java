package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public abstract class Space {

    protected Person person;
    protected Vehicle vehicle;

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
        return person == null || vehicle == null;
    }

}
