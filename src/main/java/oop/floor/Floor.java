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
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static oop.base_entities.Vehicle.isFormattedCurrectly;

public interface Floor extends Comparable<Floor>, Iterable<Space>, Collection<Space> {

    boolean add(Space abstractSpace);

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

    default boolean hasSpace(String registrationNumber) {
        return get(registrationNumber) != null;
    }

    AbstractSpace set(int index, AbstractSpace abstractSpace);

    AbstractSpace remove(int index);

    AbstractSpace remove(String registrationNumber);

    int size();

    Object[] toArray();

    default ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Space space : this) {
            result.add(space.getVehicle());
        }
        return result;
    }

    default ArrayList<Space> getSpaces(VehicleTypes vehicleType) {
        ArrayList<Space> result = new ArrayList<>();
        for (Space space : this) {
            if (space.getVehicle().getType().equals(vehicleType)) {
                result.add(space);
            }
        }
        return result;
    }

    default ArrayList<Space> getEmptySpaces() {
        ArrayList<Space> result = new ArrayList<>();
        for (Space space : this) {
            if (space.IsEmpty()) {
                result.add(space);
            }
        }
        return result;
    }

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

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default boolean contains(Object o) {
        for (Space space : this) {
            if (space.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default <T> T[] toArray(T[] a) {
        try {
            return (T[]) toArray();
        } catch (ClassCastException e) {
            throw new ArrayStoreException();
        }
    }

    @Override
    default boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return remove(indexOf((Space) o)) != null;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            boolean averageResult = false;
            for (Space space : this) {
                averageResult = space.equals(o);
                if (averageResult) {
                    break;
                }
            }
            if (!averageResult) {
                return false;
            }
        }
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends Space> c) {
        boolean result = true;
        for (Object o : c) {
            result = result && add((Space) o);
        }
        return result;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        ArrayList<Space> listToRemove = new ArrayList<>();
        for (Space space : this) {
            if (!c.contains(space)) {
                listToRemove.add(space);
            }
        }
        removeAll(listToRemove);
        return true;
    }

    @Override
    void clear();
}
