package oop.floor;

import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.space.AbstractSpace;
import oop.space.Space;

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
}
