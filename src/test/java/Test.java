import oop.Parking;
import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.base_entities.VehicleTypes;
import oop.floor.Floor;
import oop.floor.OwnersFloor;
import oop.floor.RentedSpacesFloor;
import oop.space.OwnedSpace;
import oop.space.RentedSpace;
import oop.space.AbstractSpace;
import oop.space.Space;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class Test {
    final int SPACES_ON_FLOOR_QUANTITY = 20,
            FLOORS_QUANTITY = 10;

    Person testPerson = new Person("Name", "NotName");
    Vehicle testVehicle = new Vehicle("12345", "Reno", "Logan", VehicleTypes.TRUCK);

    RentedSpace testRentedSpace = new RentedSpace(testPerson, testVehicle);
    OwnedSpace testSpace = new OwnedSpace(testPerson, testVehicle);

    RentedSpacesFloor testRentedFloor = new RentedSpacesFloor(testSpace);
    OwnersFloor testFloor = new OwnersFloor(testSpace);


    Floor[] floors;
    Parking parking;

    public Test() {
        floors = new Floor[FLOORS_QUANTITY];
        for (int j = 0; j < floors.length; j++) {
            AbstractSpace[] abstractSpaces = new AbstractSpace[SPACES_ON_FLOOR_QUANTITY];
            for (int i = 0; i < abstractSpaces.length; i++) {
                if (i % 2 == 0) {
                    abstractSpaces[i] = new OwnedSpace(new Person("", ""), Vehicle.NO_VEHICLE);
                } else {
                    abstractSpaces[i] = new RentedSpace(new Person("", ""), Vehicle.NO_VEHICLE);
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
        assertEquals(1, parking.vehiclesQuantity(testVehicle.getType()));
        assertEquals(testFloor, parking.remove(5));

        assertArrayEquals(floors, parking.getFloors());
        assertEquals(FLOORS_QUANTITY * SPACES_ON_FLOOR_QUANTITY, parking.emptySpacesQuantity());
    }

    @org.junit.Test
    public void floorsTest() {
        Floor[] floors = parking.getFloors();
        for (int i = 0; i < floors.length; i++) {
            Floor floor1 = floors[i];
            if (i % 2 == 0) {
                OwnersFloor floor = (OwnersFloor) floor1;

                assertTrue(floor.add(testSpace));
                assertEquals(testSpace, floor.remove(testSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(5, testSpace));
                assertEquals(testSpace, floor.set(5, testSpace));

                Space[] testSpaceArray = new Space[1];
                testSpaceArray[0] = testSpace;
                assertArrayEquals(testSpaceArray, floor.getSpaces(testSpace.getVehicle().getType()));

                assertEquals(testSpace, floor.remove(5));
            } else {
                RentedSpacesFloor floor = (RentedSpacesFloor) floor1;

                assertTrue(floor.add(testRentedSpace));
                assertEquals(testRentedSpace, floor.remove(testRentedSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(6, testRentedSpace));
                assertEquals(testRentedSpace, floor.set(6, testRentedSpace));
                assertEquals(testRentedSpace, floor.get(6));
                assertEquals(testRentedSpace, floor.get(testRentedSpace.getVehicle().getRegistrationNumber()));

                Space[] testSpaceArray = new Space[1];
                testSpaceArray[0] = testRentedSpace;
                assertArrayEquals(testSpaceArray, floor.getSpaces(testRentedSpace.getVehicle().getType()));

                assertEquals(testRentedSpace, floor.remove(6));
                String ren = testRentedFloor.toString();
            }
        }
    }

}