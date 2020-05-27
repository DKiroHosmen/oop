package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public interface Space {

    Person getPerson();

    void setPerson(Person person);

    Vehicle getVehicle();

    void setVehicle(Vehicle vehicle);

    boolean IsEmpty();

}
