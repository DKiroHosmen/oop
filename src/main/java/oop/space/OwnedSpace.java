package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public class OwnedSpace extends Space {

    public OwnedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

}
