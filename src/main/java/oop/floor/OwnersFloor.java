package oop.floor;

import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.space.AbstractSpace;
import oop.space.Space;

public class OwnersFloor implements Floor {

    private AbstractSpace[] spaces;
    private int size;

    public OwnersFloor() {
        this(16);
    }

    public OwnersFloor(int initialCapasity) {
        spaces = new AbstractSpace[initialCapasity];
        size = 0;
    }

    public OwnersFloor(AbstractSpace... spaces) {
        this.spaces = spaces;
        size = spaces.length;
    }

    public boolean add(AbstractSpace abstractSpace) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length + 1];
        size++;
        System.arraycopy(spaces, 0, copy, 0, spaces.length);
        copy[spaces.length] = abstractSpace;
        spaces = copy;
        return true;
    }

    public boolean add(int index, AbstractSpace abstractSpace) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length + 1];
        size++;
        copy[index] = abstractSpace;
        int i = 0, j = 0;
        while (j < copy.length) {
            if (copy[j] == null) {
                copy[j] = spaces[i];
                i++;
            }
            j++;
        }
        spaces = copy;
        return true;
    }

    public AbstractSpace get(int index) {
        return spaces[index];
    }

    public AbstractSpace get(String registrationNumber) {
        for (AbstractSpace abstractSpace : spaces) {
            if (abstractSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return abstractSpace;
            }
        }
        return null;
    }

    public boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    public AbstractSpace set(int index, AbstractSpace abstractSpace) {
        AbstractSpace replacedAbstractSpace = null;
        for (int i = 0; i < spaces.length; i++) {
            if (i == index) {
                replacedAbstractSpace = spaces[i];
                spaces[i] = abstractSpace;
            }
        }
        return replacedAbstractSpace;
    }

    public AbstractSpace remove(int index) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length - 1];
        AbstractSpace deletedAbstractSpace = null;
        int i = 0, j = 0;
        while (i < spaces.length) {
            if (i != index) {
                copy[j] = spaces[i];
                j++;
            } else {
                deletedAbstractSpace = spaces[i];
                size--;
            }
            i++;
        }
        spaces = copy;
        return deletedAbstractSpace;
    }

    public AbstractSpace remove(String registrationNumber) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length - 1];
        AbstractSpace deletedAbstractSpace = null;
        int i = 0, j = 0;
        while (i < spaces.length) {
            if (!spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                copy[j] = spaces[i];
                j++;
            } else {
                deletedAbstractSpace = spaces[i];
                size--;
            }
            i++;
        }
        spaces = copy;
        return deletedAbstractSpace;
    }

    public int size() {
        return size;
    }

    public AbstractSpace[] getSpaces() {
        return spaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int i = 0;
        for (AbstractSpace abstractSpace : spaces) {
            vehicles[i] = abstractSpace.getVehicle();
            i++;
        }
        return vehicles;
    }

    @Override
    public Space[] getSpaces(VehicleTypes vehicleType) {
        Space[] averageResult = new Space[spaces.length];
        int i = 0;
        for (Space space : spaces) {
            if (space.getVehicle().getType().equals(vehicleType)) {
                averageResult[i] = space;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

    @Override
    public Space[] getEmptySpaces() {
        Space[] averageResult = new Space[spaces.length];
        int i = 0;
        for (Space space : spaces) {
            if (space.IsEmpty()) {
                averageResult[i] = space;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

}
