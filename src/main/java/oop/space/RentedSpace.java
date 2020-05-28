package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;

import java.time.LocalDate;
import java.time.Period;

public class RentedSpace extends AbstractSpace {

    protected LocalDate rentEndsDate;

    public RentedSpace() {
        super();
        rentEndsDate = LocalDate.now().plusDays(1);
    }

    public RentedSpace(Person person, LocalDate sinceDate, LocalDate rentEndsDate) {
        this(person, Vehicle.NO_VEHICLE, sinceDate, rentEndsDate);
    }

    public RentedSpace(Person person, Vehicle vehicle, LocalDate sinceDate, LocalDate rentEndsDate) {
        super(person, vehicle, sinceDate);
        if (rentEndsDate == null) {
            throw new NullPointerException("Одна из переданных дат была null");
        }
        if (sinceDate.isAfter(rentEndsDate)) {
            throw new IllegalArgumentException("Дата завершения аренды перед датой начала аренды");
        }
        this.rentEndsDate = rentEndsDate;
    }

    public LocalDate getRentEndsDate() {
        return rentEndsDate;
    }

    public Period beforeRentEndsDate() {
        return Period.between(LocalDate.now(), rentEndsDate);
    }

    public void setRentEndsDate(LocalDate rentEndsDate) {
        this.rentEndsDate = rentEndsDate;
    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode();
    }
}
