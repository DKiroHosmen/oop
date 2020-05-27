package oop.floor;

import oop.base_entities.Vehicle;
import oop.space.Space;

public class OwnersFloor implements Floor {

    private Space[] spaces;
    private int size;

    public OwnersFloor() {
        this(16);
    }

    public OwnersFloor(int initialCapasity) {
        spaces = new Space[initialCapasity];
        size = 0;
    }

    public OwnersFloor(Space... spaces) {
        this.spaces = spaces;
        size = spaces.length;
    }

    public boolean add(Space space) {
        Space[] copy = new Space[spaces.length + 1];
        size++;
        System.arraycopy(spaces, 0, copy, 0, spaces.length);
        copy[spaces.length] = space;
        spaces = copy;
        return true;
    }

    public boolean add(int index, Space space) {
        Space[] copy = new Space[spaces.length + 1];
        size++;
        copy[index] = space;
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

    public Space get(int index) {
        return spaces[index];
    }

    public Space get(String registrationNumber) {
        for (Space space : spaces) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return space;
            }
        }
        return null;
    }

    public boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    public Space set(int index, Space space) {
        Space replacedSpace = null;
        for (int i = 0; i < spaces.length; i++) {
            if (i == index) {
                replacedSpace = spaces[i];
                spaces[i] = space;
            }
        }
        return replacedSpace;
    }

    public Space remove(int index) {
        Space[] copy = new Space[spaces.length - 1];
        Space deletedSpace = null;
        int i = 0, j = 0;
        while (i < spaces.length) {
            if (i != index) {
                copy[j] = spaces[i];
                j++;
            } else {
                deletedSpace = spaces[i];
                size--;
            }
            i++;
        }
        spaces = copy;
        return deletedSpace;
    }

    public Space remove(String registrationNumber) {
        Space[] copy = new Space[spaces.length - 1];
        Space deletedSpace = null;
        int i = 0, j = 0;
        while (i < spaces.length) {
            if (!spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                copy[j] = spaces[i];
                j++;
            } else {
                deletedSpace = spaces[i];
                size--;
            }
            i++;
        }
        spaces = copy;
        return deletedSpace;
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int i = 0;
        for (Space space : spaces) {
            vehicles[i] = space.getVehicle();
            i++;
        }
        return vehicles;
    }

}
