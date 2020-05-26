
package oop.model;

//import java.util.*;

public class Parking {
    private OwnersFloor[] floors;
    private int size = 0;
    private Vehicle[] Vehicle;

    public boolean add(OwnersFloor floor) {
        for (int i = 0; i < floors.length; i++) {
            if (floors[i] == null) {
                floors[i] = floor;

                return true;
            }
        }

        return false;
    }

    public boolean add(int index, OwnersFloor floor) {
        if ((index >= floors.length) || (index < 0)) ;
        OwnersFloor[] newOwnerFloor = new OwnersFloor[floors.length + 1];
        for (int i = 0; i < floors.length; i++) {
            newOwnerFloor[i] = floors[i];
        }
        for (int i = newOwnerFloor.length; i >= index; i--) {
            newOwnerFloor[index] = newOwnerFloor[i - 1];
        }
        newOwnerFloor[index] = (OwnersFloor) floor;
        floors = newOwnerFloor;
        return true;
    }

    public OwnersFloor set(int index, OwnersFloor floor) {
        if ((index >= floors.length) || (index < 0)) ;
        this.floors[index] = (OwnersFloor) floor;
        return floor;
    }

    public OwnersFloor remove(int index) {
        if ((index >= floors.length) || (index < 0)) ;
        OwnersFloor[] newOwnerFloor = new OwnersFloor[floors.length - 1];
        for (int i = 0; i < index; i++) {
            newOwnerFloor[i] = floors[i];
        }
        for (int i = index + 1; i < floors.length; i++) {
            newOwnerFloor[i - 1] = floors[i];
        }
        floors = newOwnerFloor;
        return null;
    }

    public int size() {
        return floors.length;
    }

    public OwnersFloor[] getFloors() {
        return floors;
    }

    public Vehicle[] getVehicle() {
        return Vehicle;
    }

    public OwnersFloor[] sortedBySizeFloors() {
        for (int i = floors.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(String.valueOf(floors[j])) > Integer.parseInt(String.valueOf(floors[j + 1]))) {
                    size = Integer.parseInt(String.valueOf(floors[j]));
                    floors[j] = floors[j + 1];
                    floors[j + 1] = size;
                }
            }
        }
        return new OwnersFloor[0];
    }

    public Space remove(String registrationNumber) {
        int j = 0;
        for (; j < floors.length; j++)
            if (floors.length == Integer.parseInt(registrationNumber))
                break;
        for (int k = j; k < floors.length - 1; k++)
            floors[k] = floors[k + 1];
        OwnersFloor[] newFloors = new OwnersFloor[floors.length - 1];
        floors = newFloors;
        return null;
    }

}

