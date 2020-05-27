package oop.floor;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.exeptions.NoRentedSpaceException;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.time.LocalDate;
import java.util.Collection;

public interface Floor {

    boolean add(AbstractSpace abstractSpace);

    boolean add(int index, AbstractSpace abstractSpace);

    AbstractSpace get(int index);

    AbstractSpace get(String registrationNumber);

    boolean hasSpace(String registrationNumber);

    AbstractSpace set(int index, AbstractSpace abstractSpace);

    AbstractSpace remove(int index);

    AbstractSpace remove(String registrationNumber);

    int size();

    AbstractSpace[] getSpaces();

    Vehicle[] getVehicles();

    Space[] getSpaces(VehicleTypes vehicleType);

    Space[] getEmptySpaces();

    boolean remove(Space space);

    int indexOf(Space space);

    int spacesQuantity(Person person);

    LocalDate nearestRentEndsDate() throws NoRentedSpaceException;

    Space spaceWithNearestRentEndDate() throws NoRentedSpaceException;

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    Object clone() throws CloneNotSupportedException;

    @Override
    String toString();
}
