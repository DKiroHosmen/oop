package oop.floor;

import oop.base_entities.Vehicle;
import oop.space.Space;

public class RentedSpacesFloor implements Floor {

    private Node<Space> head;
    private int size;

    public RentedSpacesFloor() {
        head = new Node<>();
        size = 0;
    }

    public RentedSpacesFloor(Space... spaces) {
        head = new Node<>(spaces[0]);
        Node<Space> node = head;
        for (int i = 1; i < (size = spaces.length); node = node.next, i++) {
            Node<Space> newNode = new Node<>(spaces[i]);
            newNode.previous = node;
            node.next = newNode;
        }
    }

    public boolean add(Space space) {
        size++;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
        }
        Node<Space> newNode = new Node<>(space);
        newNode.previous = node;
        node.next = newNode;
        return true;
    }

    public boolean add(int index, Space space) {
        int i = 0;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                Node<Space> newNode = new Node<>(space);
                newNode.next = node.next;
                newNode.previous = node.previous;
                node.previous.next = newNode;
                return true;
            }
        }
        return false;
    }

    public Space get(int index) {
        int i = 0;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                return node.value;
            }
        }
        return null;
    }

    public Space get(String registrationNumber) {
        for (Node<Space> node = head; node.next != null; node = node.next) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return node.value;
            }
        }
        return null;
    }

    public boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    public Space set(int index, Space space) {
        Space replacedSpace = null;
        int i = 0;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                replacedSpace = node.value;
                node.value = space;
            }
        }
        return replacedSpace;
    }

    public Space remove(int index) {
        Space deletedSpace = null;
        int i = 0;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
            i++;
            if (i == index) {
                deletedSpace = node.value;
                node.previous = node.next;
            }
        }
        return deletedSpace;
    }

    public Space remove(String registrationNumber) {
        Space deletedSpace = null;
        Node<Space> node = head;
        while (node.next != null) {
            node = node.next;
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                deletedSpace = node.value;
                node.previous = node.next;
            }
        }
        return deletedSpace;
    }

    public int size() {
        return size;
    }

    public Space[] getSpaces() {
        Space[] spaces = new Space[size];
        int i = 0;
        for (Node<Space> node = head; node.next != null; node = node.next, i++) {
            spaces[i] = node.value;
        }
        return spaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int i = 0;
        for (Node<Space> node = head; node.next != null; node = node.next, i++) {
            vehicles[i] = node.value.getVehicle();
        }
        return vehicles;
    }

}
