package oop.model;

public class OwnersFloor {
    private Space[] spaces;
    private int size = 0;
    private Vehicle[] vehicles;


    public boolean addSpace(Space space) {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == null) {
                spaces[i] = space;
                return true;
            }
        }

        return false;
    }

    public boolean addSpace(int index, Space space) {
        if (index == spaces.length)
            space = new Space();
        spaces[index] = space;
        size = index + 1;
        return true;
    }

    public Space[] getSpaces(int index) {
        return spaces;
    }

    public Space[] getSpaces(String registrationNumber) {
        return spaces;
    }

    public void hasSpace(String registrationNumber) {
        this.spaces = new Space[Integer.parseInt(registrationNumber)];
        for (int i = 0; i < Integer.parseInt(registrationNumber); i++) {
            spaces[i] = new Space();
        }
    }

    public void setSpace(int index, Space space) {
        if ((index >= spaces.length) || (index < 0)) {
            try {
                throw new Space();
            } catch (Space spaces) {
                space.printStackTrace();
            }
        }
        this.spaces[index] = (Space) space;
    }

    public Space removeSpace(int index) {
        if ((index >= spaces.length) || (index < 0)) ;
        Space[] newSpace = new Space[spaces.length - 1];
        for (int i = 0; i < index; i++) {
            newSpace[i] = spaces[i];
        }
        for (int i = index + 1; i < spaces.length; i++) {
            newSpace[i - 1] = spaces[i];
        }

        return null;
    }

    public Space[] remove(String registrationNumber) throws Exception {

        if ((Integer.parseInt(registrationNumber) >= spaces.length) || (Integer.parseInt(registrationNumber) < 0)) {
            throw new Exception(registrationNumber);//???
        }
        Space[] newSpace = new Space[spaces.length - 1];
        for (int i = 0; i < Integer.parseInt(registrationNumber); i++) {
            newSpace[i] = spaces[i];
        }
        spaces = newSpace;
        return spaces;
    }

    public int size() {
        return 0;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }
}

