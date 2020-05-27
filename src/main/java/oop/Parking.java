package oop;

import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.floor.Floor;
import oop.space.AbstractSpace;
import oop.space.Space;

public class Parking {
    Floor[] floors;
    int size;

    public Parking(int floorsQuantity) {
        floors = new Floor[floorsQuantity];
        size = 0;
    }

    public Parking(Floor[] floors) {
        this.floors = floors;
        size = floors.length;
    }

    public boolean add(Floor space) {
        Floor[] copy = new Floor[floors.length + 1];
        size++;
        for (int i = 0; i < floors.length; i++) {
            copy[i] = floors[i];
        }
        copy[floors.length] = space;
        floors = copy;
        return true;
    }

    public boolean add(int index, Floor space) {
        Floor[] copy = new Floor[floors.length + 1];
        size++;
        copy[index] = space;
        int i = 0, j = 0;
        while (j < copy.length) {
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
        return floors[index];
    }

    public Floor set(int index, Floor space) {
        Floor replacedSpace = null;
        for (int i = 0; i < floors.length; i++) {
            if (i == index) {
                replacedSpace = floors[i];
                floors[i] = space;
            }
        }
        return replacedSpace;
    }

    public Floor remove(int index) {
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
        for (Floor floor : floors)
            for (AbstractSpace abstractSpace : floor.getSpaces()) {
                vehicles[i] = abstractSpace.getVehicle();
                i++;
            }
        return vehicles;
    }

    public Floor[] sortedBySizeFloors() {
        Floor[] copy = new Floor[floors.length];
        System.arraycopy(floors, 0, copy, 0, floors.length);
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                if (copy[i].size() < copy[j].size()) {
                    Floor buffer = copy[i];
                    copy[i] = copy[j];
                    copy[j] = buffer;
                }
            }
        }
        return copy;
    }

    public AbstractSpace getSpace(String registrationNumber) {
        for (Floor floor : floors) {
            for (AbstractSpace abstractSpace : floor.getSpaces())
                if (abstractSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return abstractSpace;
                }
        }
        return null;
    }

    public AbstractSpace removeSpace(String registrationNumber) {
        AbstractSpace deletedAbstractSpace = null;
        for (Floor floor : floors) {
            for (AbstractSpace abstractSpace : floor.getSpaces())
                if (abstractSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    deletedAbstractSpace = floor.remove(registrationNumber);
                    ;
                }
        }
        return deletedAbstractSpace;
    }

    public AbstractSpace setSpace(String registrationNumber, AbstractSpace abstractSpace) {
        AbstractSpace replacedAbstractSpace = null;
        for (Floor floor : floors) {
            for (int i = 0; i < floor.getSpaces().length; i++)
                if (abstractSpace.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    replacedAbstractSpace = floor.set(i, abstractSpace);
                }
        }
        return replacedAbstractSpace;
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
}
