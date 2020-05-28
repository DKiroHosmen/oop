package oop.floor;

import oop.exeptions.RegistrationNumberFormatException;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static oop.base_entities.Vehicle.isFormattedCurrectly;

public class OwnersFloor implements Floor {

    public static final int DEFAULT_CAPASITY = 16;

    private AbstractSpace[] spaces;
    private int size;

    public OwnersFloor() {
        this(DEFAULT_CAPASITY);
    }

    public OwnersFloor(int initialCapasity) {
        spaces = new AbstractSpace[initialCapasity];
        size = 0;
    }

    public OwnersFloor(AbstractSpace... spaces) {
        this.spaces = spaces;
        size = spaces.length;
    }

    public boolean add(Space space) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length + 1];
        size++;
        System.arraycopy(spaces, 0, copy, 0, spaces.length);
        copy[spaces.length] = (AbstractSpace) space;
        spaces = copy;
        return true;
    }

    public boolean add(int index, AbstractSpace abstractSpace) {
        checkIndex(index);
        if (abstractSpace == null) {
            throw new NullPointerException();
        }
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
        checkIndex(index);
        return spaces[index];
    }

    public AbstractSpace set(int index, AbstractSpace abstractSpace) {
        checkIndex(index);
        if (abstractSpace == null) {
            throw new NullPointerException();
        }
        AbstractSpace replacedAbstractSpace = null;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                replacedAbstractSpace = spaces[i];
                spaces[i] = abstractSpace;
            }
        }
        if (replacedAbstractSpace != null) {
            return replacedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public AbstractSpace remove(int index) {
        checkIndex(index);
        AbstractSpace deletedAbstractSpace = spaces[index];
        AbstractSpace[] copy = new AbstractSpace[size - 1];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                copy[k] = spaces[i];
                k++;
            }
        }
        if (deletedAbstractSpace != null) {
            spaces = copy;
            size--;
            return deletedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public AbstractSpace remove(String registrationNumber) {
        if (registrationNumber == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        AbstractSpace[] copy = new AbstractSpace[spaces.length - 1];
        AbstractSpace deletedAbstractSpace = null;
        int i = 0, j = 0;
        while (i < size) {
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
        if (deletedAbstractSpace != null) {
            return deletedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return size;
    }

    public AbstractSpace[] toArray() {
        return spaces;
    }

    @Override
    public int hashCode() {
        int variable = spaces[0].hashCode();
        for (int i = 1; i < spaces.length; i++) {
            variable ^= spaces[i].hashCode();
        }
        return 71 * size * variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        OwnersFloor object = (OwnersFloor) obj;
        return object.size() == size &&
                Arrays.equals(object.toArray(), toArray());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Rented spaces:").append(System.lineSeparator());
        for (Space space : this) {
            result.append(space);
        }
        return result.toString();
    }

    @Override
    public void clear() {
        spaces = new AbstractSpace[DEFAULT_CAPASITY];
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int compareTo(Floor o) {
        return size - o.size();
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator();
    }

    private class SpaceIterator implements Iterator<Space> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Space next() {
            if (hasNext()) {
                Space result = spaces[cursor];
                cursor++;
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
