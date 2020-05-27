import oop.Parking;
import oop.base_entities.Person;
import oop.base_entities.Vehicle;
import oop.floor.Floor;
import oop.floor.OwnersFloor;
import oop.floor.RentedSpacesFloor;
import oop.space.OwnedSpace;
import oop.space.RentedSpace;
import oop.space.Space;

import static org.junit.Assert.*;

public class Test {
    RentedSpace testRentedSpace = new RentedSpace(
            new Person("Name", "NotName"), new Vehicle("12345", "Reno", "Logan"));
    OwnedSpace testSpace = new OwnedSpace(
            new Person("Name", "NotName"), new Vehicle("12345", "Reno", "Logan"));

    RentedSpacesFloor testRentedFloor = new RentedSpacesFloor(testSpace);
    OwnersFloor testFloor = new OwnersFloor(testSpace);


    Floor[] floors;
    Parking parking;

    public Test() {
        floors = new Floor[10];
        for (int j = 0; j < floors.length; j++) {
            Space[] spaces = new Space[20];
            for (int i = 0; i < spaces.length; i++) {
                if (i % 2 == 0) {
                    spaces[i] = new OwnedSpace(new Person("", ""), new Vehicle());
                } else {
                    spaces[i] = new RentedSpace(new Person("", ""), new Vehicle());
                }
            }
            if (j % 2 == 0) {
                floors[j] = new OwnersFloor(spaces);
            } else {
                floors[j] = new RentedSpacesFloor(spaces);
            }
        }
        parking = new Parking(floors);
    }

    @org.junit.Test
    public void parkingTest() {
        assertTrue(parking.add(testFloor));
        assertEquals(testFloor, parking.remove(parking.size() - 1));

        assertTrue(parking.add(5, testFloor));
        assertEquals(testFloor, parking.get(5));
        assertEquals(testFloor, parking.set(5, testFloor));
        assertEquals(testFloor, parking.remove(5));

        assertArrayEquals(floors, parking.getFloors());
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
                assertEquals(testSpace, floor.remove(5));
            } else {
                RentedSpacesFloor floor = (RentedSpacesFloor) floor1;

                assertTrue(floor.add(testRentedSpace));
                assertEquals(testRentedSpace, floor.remove(testRentedSpace.getVehicle().getRegistrationNumber()));

                assertTrue(floor.add(6, testRentedSpace));
                assertEquals(testRentedSpace, floor.set(6, testRentedSpace));
                assertEquals(testRentedSpace, floor.get(6));
                assertEquals(testRentedSpace, floor.get(testRentedSpace.getVehicle().getRegistrationNumber()));
                assertEquals(testRentedSpace, floor.remove(6));
            }
        }
    }

}