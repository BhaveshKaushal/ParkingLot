package test.java.com.bk.model;

import main.java.com.bk.model.Slot;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlotTest {

    @Test
    public void slotTest() {
        Slot slot = new Slot(1);
        assertNotNull(slot);
        assertEquals(1,slot.getId());
        assertNull(slot.getVehicle());
    }
}
