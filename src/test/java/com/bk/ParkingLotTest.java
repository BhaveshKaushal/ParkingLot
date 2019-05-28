package test.java.com.bk;

import main.java.com.bk.ParkingLot;
import main.java.com.bk.model.Slot;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ParkingLotTest {

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
    }

}
