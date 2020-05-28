package oop;

import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.exeptions.RegistrationNumberFormatException;
import oop.floor.Floor;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static oop.base_entities.Vehicle.isFormattedCurrectly;

public class Parking implements Iterable<Floor> {

    private static final int DEFAULT_SIZE = 0;

    Floor[] floors;
    int size;

    public Parking(int floorsQuantity) {
        floors = new Floor[floorsQuantity];
        size = DEFAULT_SIZE;
    }

    public Parking(Floor[] floors) {
        this.floors = floors;
        size = floors.length;
    }

    public boolean add(Floor space) {
        Floor[] copy = new Floor[floors.length + 1];
        size++;
        System.arraycopy(floors, 0, copy, 0, floors.length);
        copy[floors.length] = space;
        floors = copy;
        return true;
    }

    public boolean add(int index, Floor floor) {
        checkIndex(index);
        if (floor == null) {
            throw new NullPointerException();
        }
        Floor[] copy = new Floor[floors.length + 1];
        size++;
        copy[index] = floor;
        int i = 0, j = 0;
        while (j < size) {
            if (copy[j] == null) {
                copy[j] = floors[i];
                i++;
            }
            j++;
        }
        floors = copy;
        return true;
    }

    public Floor get(int index) {
        checkIndex(index);
        return floors[index];
    }

    public Floor set(int index, Floor floor) {
        checkIndex(index);
        if (floor == null) {
            throw new NullPointerException();
        }
        Floor replacedSpace = null;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                replacedSpace = floors[i];
                floors[i] = floor;
            }
        }
        return replacedSpace;
    }

    public Floor remove(int index) {
        checkIndex(index);
        Floor[] copy = new Floor[floors.length - 1];
        Floor deletedSpace = null;
        int i = 0, j = 0;
        while (i < floors.length) {
            if (i != index) {
                copy[j] = floors[i];
                j++;
            } else {
                deletedSpace = floors[i];
                size--;
            }
            i++;
        }
        floors = copy;
        return deletedSpace;
    }

    public int size() {
        return size;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int i = 0;
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                if (space != null) {
                    vehicles[i] = space.getVehicle();
                    i++;
                }
            }
        }
        return vehicles;
    }

    public Floor[] sortedBySizeFloors() {
        return Arrays.stream(floors).sorted(Floor::compareTo).toArray(Floor[]::new);
    }

    public AbstractSpace getSpace(String registrationNumber) {
        if (registrationNumber == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        for (Floor floor : floors) {
            for (AbstractSpace abstractSpace : floor.getSpaces())
                if (abstractSpace != null && abstractSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return abstractSpace;
                }
        }
        throw new NoSuchElementException();
    }

    public AbstractSpace removeSpace(String registrationNumber) {
        if (registrationNumber == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        AbstractSpace deletedAbstractSpace = null;
        for (int i = 0; i < size; i++) {
            for (AbstractSpace space : floors[i].getSpaces())
                if (space != null && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    deletedAbstractSpace = floors[i].remove(registrationNumber);
                }
        }
        if (deletedAbstractSpace != null) {
            return deletedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public AbstractSpace setSpace(String registrationNumber, AbstractSpace space) {
        if (registrationNumber == null || space == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        AbstractSpace replacedAbstractSpace = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < floors[i].getSpaces().length; j++)
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    replacedAbstractSpace = floors[i].set(j, space);
                }
        }
        if (replacedAbstractSpace != null) {
            return replacedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int emptySpacesQuantity() {
        int result = 0;
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                if (space == null || space.IsEmpty()) {
                    result++;
                }
            }
        }
        return result;
    }

    public int vehiclesQuantity(VehicleTypes vehicleType) {
        if (vehicleType == null) {
            throw new NullPointerException();
        }
        int result = 0;
        for (Floor floor : floors) {
            for (Vehicle vehicle : floor.getVehicles()) {
                if (vehicle != null && vehicle.getType().equals(vehicleType)) {
                    result++;
                }
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Floors (%s total):", size)).append(System.lineSeparator());
        for (Floor floor : floors) {
            result.append(floor);
        }
        return result.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<Floor> iterator() {
        return new FloorIterator();
    }

    private class FloorIterator implements Iterator<Floor> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Floor next() {
            if (hasNext()) {
                Floor result = floors[cursor];
                cursor++;
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
