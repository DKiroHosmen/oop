import oop.Parking;
import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.exeptions.NoRentedSpaceException;
import oop.floor.Floor;
import oop.floor.OwnersFloor;
import oop.floor.RentedSpacesFloor;
import oop.space.OwnedSpace;
import oop.space.RentedSpace;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Test {
    final int SPACES_ON_FLOOR_QUANTITY = 20,
            FLOORS_QUANTITY = 10;

    Person testPerson = new Person("Name", "NotName");
    Vehicle testVehicle = new Vehicle("M123MM45", "Reno", "Logan", VehicleTypes.CAR);

    RentedSpace testRentedSpace = new RentedSpace(testPerson, testVehicle, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2));
    OwnedSpace testOwnedSpace = new OwnedSpace(testPerson, testVehicle, LocalDate.now().minusDays(1));

    RentedSpacesFloor testRentedFloor = new RentedSpacesFloor(testOwnedSpace);
    OwnersFloor testFloor = new OwnersFloor(testOwnedSpace);


    Floor[] floors;
    Parking parking;

    public Test() {
        floors = new Floor[FLOORS_QUANTITY];
        for (int j = 0; j < floors.length; j++) {
            AbstractSpace[] abstractSpaces = new AbstractSpace[SPACES_ON_FLOOR_QUANTITY];
            for (int i = 0; i < abstractSpaces.length; i++) {
                if (i % 2 == 0) {
                    abstractSpaces[i] = new OwnedSpace(new Person("", ""), Vehicle.NO_VEHICLE,
                            LocalDate.now().minusDays(1));
                } else {
                    abstractSpaces[i] = new RentedSpace(new Person("", ""), Vehicle.NO_VEHICLE,
                            LocalDate.now().minusDays(1), LocalDate.now().plusDays(7));
                }
            }
            if (j % 2 == 0) {
                floors[j] = new OwnersFloor(abstractSpaces);
            } else {
                floors[j] = new RentedSpacesFloor(abstractSpaces);
            }
        }
        parking = new Parking(floors);
        String stringTest = parking.toString();
    }

    @org.junit.Test
    public void parkingTest() {
        assertTrue(parking.add(testFloor));
        assertEquals(testFloor, parking.remove(parking.size() - 1));

        assertTrue(parking.add(5, testFloor));
        assertEquals(testFloor, parking.get(5));
        assertEquals(testFloor, parking.set(5, testFloor));
        assertEquals(testFloor, parking.sortedBySizeFloors()[0]);
        assertEquals(1, parking.vehiclesQuantity(testVehicle.getType()));
        assertEquals(testOwnedSpace, parking.getSpace(testVehicle.getRegistrationNumber()));
        assertEquals(testOwnedSpace, parking.removeSpace(testVehicle.getRegistrationNumber()));
        assertArrayEquals(testFloor.toArray(), parking.getFloors(testPerson).toArray());
        assertEquals(testFloor, parking.remove(5));

        assertArrayEquals(floors, parking.getFloors().toArray(Floor[]::new));
        assertEquals(FLOORS_QUANTITY * SPACES_ON_FLOOR_QUANTITY, parking.emptySpacesQuantity());
    }

    @org.junit.Test
    public void floorsTest() throws NoRentedSpaceException {
        Floor[] floors = parking.getFloors().toArray(Floor[]::new);
        for (int i = 0; i < floors.length; i++) {
            Floor floor1 = floors[i];
            if (i % 2 == 0) {
                OwnersFloor floor = (OwnersFloor) floor1;

                assertTrue(floor.add(testOwnedSpace));
                assertEquals(testOwnedSpace, floor.remove(testOwnedSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(5, testOwnedSpace));
                assertEquals(5, floor.indexOf(testOwnedSpace));
                assertTrue(floor.contains(testOwnedSpace));
                assertEquals(testOwnedSpace, floor.set(5, testOwnedSpace));
                assertEquals(testOwnedSpace, floor.get(5));
                assertEquals(testOwnedSpace, floor.get(testOwnedSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(testRentedSpace));
                assertEquals(testRentedSpace.getRentEndsDate(), floor.nearestRentEndsDate());
                assertEquals(testRentedSpace, floor.spaceWithNearestRentEndDate());
                assertTrue(floor.remove(testRentedSpace));

                Space[] testSpaceArray = new Space[1];
                testSpaceArray[0] = testOwnedSpace;
                assertArrayEquals(testSpaceArray, floor.getSpaces(testOwnedSpace.getVehicle().getType()).toArray());

                assertEquals(testOwnedSpace, floor.remove(5));

                ArrayList<Space> testSpaceList = new ArrayList<>();
                testSpaceList.add(testOwnedSpace);
                testSpaceList.add(testRentedSpace);
                assertTrue(floor.addAll(testSpaceList));
                assertTrue(floor.retainAll(testSpaceList));
                assertTrue(floor.containsAll(testSpaceList));
                assertTrue(floor.removeAll(testSpaceList));

                floor.clear();
                assertTrue(floor.isEmpty());
            } else {
                RentedSpacesFloor floor = (RentedSpacesFloor) floor1;

                assertTrue(floor.add(testRentedSpace));
                assertEquals(testRentedSpace, floor.remove(testRentedSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(6, testRentedSpace));
                assertEquals(6, floor.indexOf(testRentedSpace));
                assertEquals(testRentedSpace, floor.set(6, testRentedSpace));
                assertEquals(testRentedSpace, floor.get(6));
                assertEquals(testRentedSpace, floor.get(testRentedSpace.getVehicle().getRegistrationNumber()));
                assertEquals(testRentedSpace.getRentEndsDate(), floor.nearestRentEndsDate());
                assertEquals(testRentedSpace, floor.spaceWithNearestRentEndDate());

                Space[] testSpaceArray = new Space[1];
                testSpaceArray[0] = testRentedSpace;
                assertArrayEquals(testSpaceArray, floor.getSpaces(testRentedSpace.getVehicle().getType()).toArray());

                assertEquals(testRentedSpace, floor.remove(6));
                String ren = testRentedFloor.toString();

                floor.clear();
                assertTrue(floor.isEmpty());
            }
        }
    }

}