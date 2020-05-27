package oop.floor;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.exeptions.NoRentedSpaceException;
import oop.exeptions.RegistrationNumberFormatException;
import oop.space.AbstractSpace;
import oop.space.RentedSpace;
import oop.space.Space;

import java.time.LocalDate;
import java.util.Arrays;
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

    public boolean add(AbstractSpace abstractSpace) {
        AbstractSpace[] copy = new AbstractSpace[spaces.length + 1];
        size++;
        System.arraycopy(spaces, 0, copy, 0, spaces.length);
        copy[spaces.length] = abstractSpace;
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

    public AbstractSpace get(String registrationNumber) {
        if (registrationNumber == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return spaces[i];
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
        AbstractSpace[] copy = new AbstractSpace[spaces.length - 1];
        AbstractSpace deletedAbstractSpace = null;
        int i = 0, j = 0;
        while (i < size) {
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

    public AbstractSpace[] getSpaces() {
        return spaces;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size];
        int k = 0;
        for (int i = 0; i < size; i++, k++) {
            vehicles[k] = spaces[i].getVehicle();
        }
        return vehicles;
    }

    @Override
    public Space[] getSpaces(VehicleTypes vehicleType) {
        if (vehicleType == null) {
            throw new NullPointerException();
        }
        Space[] averageResult = new Space[spaces.length];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (spaces[i].getVehicle().getType().equals(vehicleType)) {
                averageResult[k] = spaces[i];
                k++;
            }
        }
        Space[] result = new Space[k];
        System.arraycopy(averageResult, 0, result, 0, k);
        return result;
    }

    @Override
    public Space[] getEmptySpaces() {
        Space[] averageResult = new Space[spaces.length];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (spaces[i].IsEmpty()) {
                averageResult[k] = spaces[i];
                k++;
            }
        }
        Space[] result = new Space[k];
        System.arraycopy(averageResult, 0, result, 0, k);
        return result;
    }

    public boolean remove(Space space) {
        if (space == null) {
            throw new NullPointerException();
        }
        return remove(indexOf(space)) != null;
    }

    public int indexOf(Space space) {
        if (space == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (spaces[i] == space) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public int spacesQuantity(Person person) {
        if (person == null) {
            throw new NullPointerException();
        }
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (spaces[i].getPerson().equals(person)) {
                result++;
            }
        }
        return result;
    }

    public LocalDate nearestRentEndsDate() throws NoRentedSpaceException {
        LocalDate min = LocalDate.MAX;
        for (int i = 0; i < size; i++) {
            try {
                RentedSpace rentedSpace = (RentedSpace) spaces[i];
                if (rentedSpace.getRentEndsDate().isBefore(min)) {
                    min = rentedSpace.getRentEndsDate();
                }
            } catch (ClassCastException ignore) {
            }
        }
        if (min == LocalDate.MAX) {
            throw new NoRentedSpaceException("Не удалось найти место");
        } else {
            return min;
        }
    }

    public Space spaceWithNearestRentEndDate() throws NoRentedSpaceException {
        LocalDate nearestDate = nearestRentEndsDate();
        for (int i = 0; i < size; i++) {
            try {
                if (((RentedSpace) spaces[i]).getRentEndsDate().equals(nearestDate)) {
                    return spaces[i];
                }
            } catch (ClassCastException ignore) {
            }
        }
        throw new NoRentedSpaceException("Не удалось найти место");
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
        for (int i = 0; i < size; i++) {
            result.append(spaces[i]);
        }
        return result.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
