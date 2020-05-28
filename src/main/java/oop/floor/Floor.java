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
import java.util.NoSuchElementException;

import static oop.base_entities.Vehicle.isFormattedCurrectly;

public interface Floor extends Comparable<Floor>, Iterable<Space> {

    boolean add(AbstractSpace abstractSpace);

    boolean add(int index, AbstractSpace abstractSpace);

    AbstractSpace get(int index);

    default AbstractSpace get(String registrationNumber) {
        if (registrationNumber == null) {
            throw new NullPointerException();
        }
        if (!isFormattedCurrectly(registrationNumber)) {
            throw new RegistrationNumberFormatException();
        }
        for (Space space : this) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return (AbstractSpace) space;
            }
        }
        throw new NoSuchElementException();
    }

    boolean hasSpace(String registrationNumber);

    AbstractSpace set(int index, AbstractSpace abstractSpace);

    AbstractSpace remove(int index);

    AbstractSpace remove(String registrationNumber);

    int size();

    AbstractSpace[] getSpaces();

    default Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[size()];
        int i = 0;
        for (Space space : this) {
            vehicles[i] = space.getVehicle();
            i++;
        }
        return vehicles;
    }

    default Space[] getSpaces(VehicleTypes vehicleType) {
        if (vehicleType == null) {
            throw new NullPointerException();
        }
        Space[] averageResult = new Space[size()];
        int i = 0;
        for (Space space : this) {
            if (space.getVehicle().getType().equals(vehicleType)) {
                averageResult[i] = space;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

    default Space[] getEmptySpaces() {
        Space[] averageResult = new Space[size()];
        int i = 0;
        for (Space space : this) {
            if (space.IsEmpty()) {
                averageResult[i] = space;
                i++;
            }
        }
        Space[] result = new Space[i];
        System.arraycopy(averageResult, 0, result, 0, i);
        return result;
    }

    boolean remove(Space space);

    default int indexOf(Space space) {
        if (space == null) {
            throw new NullPointerException();
        }
        int i = 0;
        for (Space space1 : this) {
            if (space1 == space) {
                return i;
            }
            i++;
        }
        return -1;
    }

    default int spacesQuantity(Person person) {
        if (person == null) {
            throw new NullPointerException();
        }
        int result = 0;
        for (Space space : this) {
            if (space.getPerson().equals(person)) {
                result++;
            }
        }
        return result;
    }

    default LocalDate nearestRentEndsDate() throws NoRentedSpaceException {
        LocalDate min = LocalDate.MAX;
        for (Space space : this) {
            try {
                RentedSpace rentedSpace = (RentedSpace) space;
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

    default Space spaceWithNearestRentEndDate() throws NoRentedSpaceException {
        LocalDate nearestDate = nearestRentEndsDate();
        for (Space space : this) {
            try {
                if (((RentedSpace) space).getRentEndsDate().equals(nearestDate)) {
                    return space;
                }
            } catch (ClassCastException ignore) {
            }
        }
        throw new NoRentedSpaceException("Не удалось найти место");
    }

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    Object clone() throws CloneNotSupportedException;

    @Override
    String toString();
}
