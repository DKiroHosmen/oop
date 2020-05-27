package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public class RentedSpace extends Space {

    public RentedSpace() {
        this(Person.NO_NAME, new Vehicle());
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

}
