package com.bk.model;

import com.bk.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlotTest extends BaseTest {

    @Test
    public void slotTest() {

        Slot slot = new Slot(1);
        assertNotNull(slot);
        assertEquals(1,slot.getSlotNumber());
        assertNull(slot.getVehicle());


    }
}
