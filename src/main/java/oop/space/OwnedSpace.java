package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public class OwnedSpace extends AbstractSpace {

    public OwnedSpace() {
        super();
    }

    public OwnedSpace(Person person) {
        super(person);
    }

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    @Override
    public int hashCode() {
        return 71 * super.hashCode();
    }
}
