package test.java.com.bk;

import main.java.com.bk.ParkingLot;
import main.java.com.bk.model.Slot;
import main.java.com.bk.model.Vehicle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private void testSlotInitialization(List<Slot> slotList, int size) {

        for(int i = 0; i < size; i++) {
            Slot slot =  slotList.get(i);
            assertNotNull(slot);
            assertEquals(i+1,slot.getSlotNumber());
            assertNull(slot.getVehicle());
        }
    }

    @Test
    public void parkingLotTest() {
        int size =10;
        ParkingLot parkingLot =  new ParkingLot(size);
        assertNotNull(parkingLot);
        assertEquals(size,parkingLot.getSize());

        Set<Slot> availableSlots=parkingLot.getAvailableSlots();
        assertNotNull(availableSlots);
        assertFalse(availableSlots.isEmpty());
        assertEquals(size,availableSlots.size());

        List<Slot> slotList =  parkingLot.getSlotList();

        assertNotNull(slotList);
        assertFalse(slotList.isEmpty());
        assertEquals(size,slotList.size());

        //test all initialized with slot Numbers and not allocated to any vehicle
        testSlotInitialization(slotList,size);


        Slot[] slotArray = new Slot[size];
        availableSlots.toArray(slotArray);

        //test the order of the available slots and all slots available slot
        // should be sorted based on distance from the entry
        testSlotInitialization(Arrays.asList(slotArray), size);

    }

    @Test
    public void parkTest() {
        int size = 3;
        ParkingLot parkingLot = new ParkingLot(size);

        assertNotNull(parkingLot);
        assertNotNull(parkingLot.getAvailableSlots());
        assertNotNull(parkingLot.getSlotList());
        assertEquals(size, parkingLot.getAvailableSlots().size());
        assertEquals(size,parkingLot.getSlotList().size());

        String registrationNumber = "test-number";
        String color = "white";
        int slotNumber = parkingLot.parkVehicle(registrationNumber,color);
        assertNotNull(parkingLot.getAvailableSlots());
        assertEquals(2,parkingLot.getAvailableSlots().size());
        assertNotNull(parkingLot.getAvailableSlots().first());
        assertEquals(2,parkingLot.getAvailableSlots().first().getSlotNumber());
        Slot reservedSlot = parkingLot.getSlotList().get(0);
        assertNotNull(reservedSlot);
        assertEquals(slotNumber, reservedSlot.getSlotNumber());

        Vehicle vehicle = reservedSlot.getVehicle();
        assertNotNull(vehicle);
        assertEquals(color, vehicle.getColor());
        assertEquals(registrationNumber,vehicle.getRegistrationNumber());
        assertEquals(1,parkingLot.getSlotNumberByRegistrationNumber(registrationNumber));

    }

    @Test
    public void parkingFullTest() {
        int size = 3;

        ParkingLot parkingLot = new ParkingLot(size);

        int slotNumber = parkingLot.parkVehicle("test_reg1","white");
        assertEquals(1, slotNumber);

        int slotNumber2 = parkingLot.parkVehicle("test_reg2","black");
        assertEquals(2,slotNumber2);


        int slotNumber3 = parkingLot.parkVehicle("test_reg3","blue");
        assertEquals(3,slotNumber3);

        int slotNumber4 = parkingLot.parkVehicle("parking_full","red");
        assertEquals(-1,slotNumber4);

    }


}
