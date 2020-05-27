package oop.floor;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.util.Arrays;

public class RentedSpacesFloor implements Floor {

    private Node<AbstractSpace> head;
    private int size;

    public RentedSpacesFloor() {
        head = new Node<>();
        size = 0;
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
        int i = 0;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                Node<AbstractSpace> newNode = new Node<>(abstractSpace);
                newNode.next = node.next;
                newNode.previous = node.previous;
                node.previous.next = newNode;
                size++;
                return true;
            }
        }
        return false;
    }

    public AbstractSpace get(int index) {
        int i = 0;
        Node<AbstractSpace> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                return node.value;
            }
        }
        return null;
    }

    public AbstractSpace get(String registrationNumber) {
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return node.value;
            }
        }
        return null;
    }

    public boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    public AbstractSpace set(int index, AbstractSpace abstractSpace) {
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
        return replacedAbstractSpace;
    }

    public AbstractSpace remove(int index) {
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
        return deletedAbstractSpace;
    }

    public AbstractSpace remove(String registrationNumber) {
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
        return deletedAbstractSpace;
    }

    public int size() {
        return size;
    }

    public AbstractSpace[] getSpaces() {
        AbstractSpace[] abstractSpaces = new AbstractSpace[size];
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next, i++) {
            abstractSpaces[i] = node.value;
        }
        return abstractSpaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next, i++) {
            vehicles[i] = node.value.getVehicle();
        }
        return vehicles;
    }

    public Space[] getSpaces(VehicleTypes vehicleType) {
        Space[] averageResult = new Space[size];
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next) {
            if (node.value.getVehicle().getType().equals(vehicleType)) {
                averageResult[i] = node.value;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

    public Space[] getEmptySpaces() {
        Space[] averageResult = new Space[size];
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next) {
            if (node.value.IsEmpty()) {
                averageResult[i] = node.value;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

    public boolean remove(Space space) {
        return remove(indexOf(space)) == null;
    }

    public int indexOf(Space space) {
        int i = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next, i++) {
            if (node.value == space) {
                return i;
            }
        }
        return -1;
    }

    public int spacesQuantity(Person person) {
        int result = 0;
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next) {
            if (node.value.getPerson().equals(person)) {
                result++;
            }
        }
        return result;
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
        for (Node<AbstractSpace> node = head; node.next != null; node = node.next) {
            result.append(node.value);
        }
        return result.toString();
    }
}
