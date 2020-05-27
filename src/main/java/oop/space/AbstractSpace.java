package oop.space;

import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;

import java.time.LocalDate;
import java.time.Period;

public abstract class AbstractSpace implements Space {

    protected Person person;
    protected Vehicle vehicle;
    protected LocalDate sinceDate;

    public AbstractSpace() {
        this(Person.NO_NAME, Vehicle.NO_VEHICLE, LocalDate.now());
    }

    public AbstractSpace(Person person, LocalDate sinceDate) {
        this(person, Vehicle.NO_VEHICLE, sinceDate);
    }

    public AbstractSpace(Person person, Vehicle vehicle, LocalDate sinceDate) {
        if (sinceDate == null) {
            throw new NullPointerException("Переданная дата была Null");
        }
        if (sinceDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Указанная дата после текущей");
        }
        this.person = person;
        this.vehicle = vehicle;
        this.sinceDate = sinceDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean IsEmpty() {
        return vehicle.equals(Vehicle.NO_VEHICLE) || vehicle.getType().equals(VehicleTypes.NONE);
    }

    public LocalDate getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(LocalDate sinceDate) {
        this.sinceDate = sinceDate;
    }

    public Period period() {
        return Period.between(sinceDate, LocalDate.now());
    }

    @Override
    public int hashCode() {
        return person.hashCode() *
                vehicle.hashCode() *
                sinceDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        AbstractSpace object = (AbstractSpace) obj;
        return object.getPerson().equals(person) &&
                object.getVehicle().equals(vehicle) &&
                object.getSinceDate().equals(sinceDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("Tenant: %s ТС: %s SinceDate: %s", person, vehicle, sinceDate);
    }
}
