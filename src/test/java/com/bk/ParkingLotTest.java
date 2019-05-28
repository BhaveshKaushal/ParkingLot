package test.java.com.bk;

import main.java.com.bk.ParkingLot;
import main.java.com.bk.model.Slot;
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
            assertEquals(i,slot.getSlotNumber());
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



}
