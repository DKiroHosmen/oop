package oop.floor;

import oop.exeptions.NoRentedSpaceException;
import oop.exeptions.RegistrationNumberFormatException;
import oop.space.AbstractSpace;
import oop.space.RentedSpace;
import oop.space.Space;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static oop.base_entities.Vehicle.isFormattedCurrectly;

public class RentedSpacesFloor implements Floor {

    private static final int DEFAULT_SIZE = 0;

    private Node<AbstractSpace> head;
    private int size;

    public RentedSpacesFloor() {
        head = new Node<>();
        size = DEFAULT_SIZE;
    }

    public RentedSpacesFloor(AbstractSpace... abstractSpaces) {
        head = new Node<>(abstractSpaces[0]);
        Node<AbstractSpace> node = head;
        for (int i = 1; i < (size = abstractSpaces.length); node = node.next, i++) {
            Node<AbstractSpace> newNode = new Node<>(abstractSpaces[i]);
            newNode.previous = node;
            node.next = newNode;
        }
    }

    public boolean add(AbstractSpace abstractSpace) {
        size++;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
        }
        Node<AbstractSpace> newNode = new Node<>(abstractSpace);
        newNode.previous = node;
        node.next = newNode;
        return true;
    }

    public boolean add(int index, AbstractSpace abstractSpace) {
        checkIndex(index);
        if (abstractSpace == null) {
            throw new NullPointerException();
        }
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next, i++) {
            if (i == index) {
                Node<AbstractSpace> newNode = new Node<>(abstractSpace);
                newNode.previous = node.previous;
                newNode.next = node;
                node.previous.next = newNode;

                size++;
                return true;
            }
        }
        return false;
    }

    public AbstractSpace get(int index) {
        checkIndex(index);
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next, i++) {
            if (i == index) {
                return node.value;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    public AbstractSpace set(int index, AbstractSpace abstractSpace) {
        checkIndex(index);
        if (abstractSpace == null) {
            throw new NullPointerException();
        }
        AbstractSpace replacedAbstractSpace = null;
        int i = 0;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                replacedAbstractSpace = node.value;
                node.value = abstractSpace;
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
        AbstractSpace deletedAbstractSpace = null;
        int i = 0;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                deletedAbstractSpace = node.value;
                node.previous = node.next;
                size--;
            }
        }
        if (deletedAbstractSpace != null) {
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
        AbstractSpace deletedAbstractSpace = null;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                deletedAbstractSpace = node.value;
                node.previous = node.next;
                size--;
            }
        }
        if (deletedAbstractSpace != null) {
            return deletedAbstractSpace;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return size;
    }

    public AbstractSpace[] getSpaces() {
        AbstractSpace[] abstractSpaces = new AbstractSpace[size];
        int i = 0;
        for (Space space : this) {
            abstractSpaces[i] = (AbstractSpace) space;
            i++;
        }
        return abstractSpaces;
    }

    public boolean remove(Space space) {
        return remove(indexOf(space)) == null;
    }

    @Override
    public int hashCode() {
        int variable = head.hashCode();
        for (Node<AbstractSpace> node = head.next; node.next != null; node = node.next) {
            variable ^= node.hashCode();
        }
        return 53 * size * variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        RentedSpacesFloor object = (RentedSpacesFloor) obj;
        return object.size() == size &&
                Arrays.equals(object.getSpaces(), getSpaces());
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
        Node<AbstractSpace> currentNode = head;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Space next() {
            if (hasNext()) {
                if (cursor != 0) {
                    currentNode = currentNode.next;
                }
                cursor++;
                return currentNode.value;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}

