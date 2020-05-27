package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

public class RentedSpace extends AbstractSpace {

    public RentedSpace() {
        super();
    }

    public RentedSpace(Person person) {
        super(person);
    }

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle);
    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode();
    }
}
