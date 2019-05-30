package com.bk.processor;

import com.bk.BaseTest;
import com.bk.ParkingLot;
import com.bk.model.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class BaseProcessorTest extends BaseTest {

   private static BaseProcessor baseProcessor;

   private static ParkingLot parkingLot;

    @Before
   public void  setUpBeforeAll() {
         baseProcessor = new BaseProcessor();
         setUpParkingLot();
    }

    @Test
    public void initializeParkingLotTest() {

        setUpParkingLot();
        assertNotNull(parkingLot);
        assertEquals(size,parkingLot.getSize());
        assertEquals(size, parkingLot.getSlotMap().size());

    }
    @Test
    public void parkTest() {

        try {
            parkOneVehicle();

        } catch (Exception e) {
            assertNull(e);
        }

    }

    @Test
    public void leaveTest() {
        fillUpParking();
        int slotNumber = parkingLot.freeSlot(1);
        assertEquals(1,slotNumber);
        assertEquals(1, parkingLot.getNextFreeSlot());
        assertNotNull(parkingLot.getRegistrationNumbersByColor(color1));
        assertTrue(parkingLot.getRegistrationNumbersByColor(color1).isEmpty());
        assertNotNull(parkingLot.getSlotNumbersByColor(color1));
        assertTrue(parkingLot.getSlotNumbersByColor(color1).isEmpty());
       assertEquals(-1, parkingLot.getSlotNumberByRegistrationNumber(color1));


    }

    @Test
    public void parkingFull() {
        fillUpParking();
        String line = "park park_full_test white";
       baseProcessor.process(baseProcessor.getInputs(line));
       assertEquals(-1, parkingLot.getNextFreeSlot());
    }

    @Test(expected = IllegalArgumentException.class )
    public void invalidOperationOrderTest() {

        String line = "park park_full_test white";
       BaseProcessor baseProcessor =  new BaseProcessor();
       baseProcessor.process(baseProcessor.getInputs(line));

    }


    private static void setUpParkingLot() {

        String line = String.format("%s %d", Operation.CREATE_PARKING_LOT.getValue(), size);
        String[] inputs = baseProcessor.getInputs(line);
        baseProcessor.process(inputs);

        parkingLot = baseProcessor.getParkingLot();


    }

    private void fillUpParking() {

        String parkLine = String.format("%s %s %s",Operation.PARK.getValue(),reg1, color1);
        baseProcessor.process(baseProcessor.getInputs(parkLine));
        assertEquals(2,parkingLot.getNextFreeSlot());
        assertEquals(1, parkingLot.getSlotNumberByRegistrationNumber(reg1));
        assertEquals(Collections.singletonList(1),parkingLot.getSlotNumbersByColor(color1));
        assertEquals(Collections.singletonList(reg1),parkingLot.getRegistrationNumbersByColor(color1));

        String parkLine2 = String.format("%s %s %s",Operation.PARK.getValue(),reg2, color2);
        baseProcessor.process(baseProcessor.getInputs(parkLine2));
        assertEquals(3,parkingLot.getNextFreeSlot());
        assertEquals(2, parkingLot.getSlotNumberByRegistrationNumber(reg2));
        assertEquals(Collections.singletonList(2),parkingLot.getSlotNumbersByColor(color2));
        assertEquals(Collections.singletonList(reg2),parkingLot.getRegistrationNumbersByColor(color2));

        String parkLine3 = String.format("%s %s %s",Operation.PARK.getValue(),reg3, color3);
        baseProcessor.process(baseProcessor.getInputs(parkLine3));
        assertEquals(-1,parkingLot.getNextFreeSlot());
        assertEquals(3, parkingLot.getSlotNumberByRegistrationNumber(reg3));
        assertEquals(Collections.singletonList(3),parkingLot.getSlotNumbersByColor(color3));
        assertEquals(Collections.singletonList(reg3),parkingLot.getRegistrationNumbersByColor(color3));

    }

    private void parkOneVehicle() {

        String parkLine = String.format("%s %s %s",Operation.PARK.getValue(),reg1, color1);
        baseProcessor.process(baseProcessor.getInputs(parkLine));
        assertEquals(2,parkingLot.getNextFreeSlot());
        assertEquals(1, parkingLot.getSlotNumberByRegistrationNumber(reg1));
        assertEquals(Collections.singletonList(1),parkingLot.getSlotNumbersByColor(color1));
        assertEquals(Collections.singletonList(reg1),parkingLot.getRegistrationNumbersByColor(color1));
    }
}