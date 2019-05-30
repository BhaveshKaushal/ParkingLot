package com.bk;

import com.bk.model.Slot;
import com.bk.model.Ticket;
import com.bk.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ParkingLotTest extends BaseTest {

    private ParkingLot parkingLot;

    private void testSlotInitialization(Map<Integer, Slot> slotMap, int size) {

        for(int i = 0; i < size; i++) {
            Slot slot =  slotMap.get(i+1);
            assertNotNull(slot);
            assertEquals(i+1,slot.getSlotNumber());
            assertNull(slot.getVehicle());
        }
    }

    @Before
   public void setUp() {
        this.parkingLot = new ParkingLot(size);
    }

    @Test
    public void parkingLotTest() {

        assertNotNull(parkingLot);
        assertEquals(size,parkingLot.getSize());

        int nextFreeSlot =parkingLot.getNextFreeSlot();
        assertEquals(1,nextFreeSlot);

        Map<Integer,Slot> slotMap =  parkingLot.getSlotMap();

        assertNotNull(slotMap);
        assertFalse(slotMap.isEmpty());
        assertEquals(size,slotMap.size());

        //test all initialized with slot Numbers and not allocated to any vehicle
        testSlotInitialization(slotMap,size);

    }

    @Test
    public void parkTest() {

        Ticket ticket = parkingLot.parkVehicle(reg1,color1);
       assertEquals(2,parkingLot.getNextFreeSlot());

        Slot reservedSlot = parkingLot.getSlotMap().get(ticket.getSlot());
        assertNotNull(reservedSlot);
        assertEquals(ticket.getSlot(), reservedSlot.getSlotNumber());

        Vehicle vehicle = reservedSlot.getVehicle();
        assertNotNull(vehicle);
        assertEquals(color1, vehicle.getColor());
        assertEquals(reg1,vehicle.getRegistrationNumber());
        assertEquals(1,parkingLot.getSlotNumberByRegistrationNumber(reg1));

    }

    @Test
    public void parkingFullTest() {

        assertEquals(3,parkingLot.getSlotMap().size());
        Ticket ticket = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1, ticket.getSlot());
        assertEquals(2,parkingLot.getNextFreeSlot());
        assertEquals(3,parkingLot.getSlotMap().size());

        Ticket ticket2 = parkingLot.parkVehicle(reg2,color2);
        assertEquals(2,ticket2.getSlot());
        assertEquals(3,parkingLot.getNextFreeSlot());


        Ticket ticket3 = parkingLot.parkVehicle(reg3,color3);
        assertEquals(3,ticket3.getSlot());
        assertEquals(-1,parkingLot.getNextFreeSlot());

        Ticket ticket4 = parkingLot.parkVehicle("parking_full","blue");
       assertNull(ticket4);


    }



    @Test
    public void freeSlotTest() {

        Ticket ticket = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1,ticket.getSlot());
        assertEquals(2, parkingLot.getNextFreeSlot());
        assertEquals(ticket.getSlot(),parkingLot.getSlotNumberByRegistrationNumber(reg1));
        assertEquals(reg1,parkingLot.getSlotMap().get(ticket.getSlot()).getVehicle().getRegistrationNumber());

        Ticket ticket2 = parkingLot.parkVehicle(reg2,color2);
        assertEquals(2,ticket2.getSlot());
        assertEquals(3, parkingLot.getNextFreeSlot());
        assertEquals(ticket2.getSlot(),parkingLot.getSlotNumberByRegistrationNumber(reg2));
        assertEquals(reg2,parkingLot.getSlotMap().get(ticket2.getSlot()).getVehicle().getRegistrationNumber());


        Ticket ticket3 = parkingLot.parkVehicle(reg3,color3);
        assertEquals(3,ticket3.getSlot());
        assertEquals(-1, parkingLot.getNextFreeSlot());
        assertEquals(ticket3.getSlot(),parkingLot.getSlotNumberByRegistrationNumber(reg3));
        assertEquals(reg3,parkingLot.getSlotMap().get(ticket3.getSlot()).getVehicle().getRegistrationNumber());

        int freedSlotNumber = parkingLot.freeSlot(2);
        assertNotEquals(-1,freedSlotNumber);
        assertEquals(2,parkingLot.getNextFreeSlot());
        assertEquals(-1, parkingLot.getSlotNumberByRegistrationNumber(reg2));
        assertNull(parkingLot.getSlotMap().get(freedSlotNumber).getVehicle());

        //check the next free slit always updates to nearest slot
        int freedSlotNumber2 = parkingLot.freeSlot(1);
        assertNotEquals(-1,freedSlotNumber2);
        assertEquals(1,parkingLot.getNextFreeSlot());
        assertEquals(-1, parkingLot.getSlotNumberByRegistrationNumber(reg1));
        assertNull(parkingLot.getSlotMap().get(freedSlotNumber2).getVehicle());


    }

    @Test
    public void getSlotListbyColorTest(){

        fillUpParking();
       List<Integer> redSlotNumbers  = parkingLot.getSlotNumbersByColor(color1);
       assertNotNull(redSlotNumbers);
       assertFalse(redSlotNumbers.isEmpty());
       assertEquals(2, redSlotNumbers.size());
       assertEquals(Arrays.asList(1,2),redSlotNumbers);

       List<Integer> blackSlotNumbers = parkingLot.getSlotNumbersByColor(color2);
        assertNotNull(blackSlotNumbers);
        assertFalse(blackSlotNumbers.isEmpty());
        assertEquals(1, blackSlotNumbers.size());
        assertEquals(Collections.singletonList(3),blackSlotNumbers);

        int freedBlackSlot =  parkingLot.freeSlot(blackSlotNumbers.get(0));
        assertEquals(3,freedBlackSlot);

        List<Integer> noBlackSlotNumberList = parkingLot.getSlotNumbersByColor(color2);
        assertNotNull(noBlackSlotNumberList);
        assertTrue(noBlackSlotNumberList.isEmpty());

        int freedRedSlot = parkingLot.freeSlot(redSlotNumbers.get(0));
        assertEquals(1,freedRedSlot);
        redSlotNumbers = parkingLot.getSlotNumbersByColor(color1);

        assertFalse(redSlotNumbers.isEmpty());
        assertEquals(1,redSlotNumbers.size());
        assertEquals(2,redSlotNumbers.get(0).longValue());
    }

    @Test
    public void getRegistrationListByColor(){

        fillUpParking();
        List<String> redRegistrationList = parkingLot.getRegistrationNumbersByColor(color1);
        assertNotNull(redRegistrationList);
        assertFalse(redRegistrationList.isEmpty());
        assertEquals(2, redRegistrationList.size());
        assertEquals(Arrays.asList(reg1,reg2),redRegistrationList);

        List<String> blackSlotNumbers = parkingLot.getRegistrationNumbersByColor(color2);
        assertNotNull(blackSlotNumbers);
        assertFalse(blackSlotNumbers.isEmpty());
        assertEquals(1, blackSlotNumbers.size());
        assertEquals(Collections.singletonList(reg3),blackSlotNumbers);

        int freedBlackSlot =  parkingLot.freeSlot(3);
        assertEquals(3,freedBlackSlot);

        List<Integer> noBlackSlotNumberList = parkingLot.getSlotNumbersByColor(color2);
        assertNotNull(noBlackSlotNumberList);
        assertTrue(noBlackSlotNumberList.isEmpty());


        int freedRedSlot = parkingLot.freeSlot(1);
        assertEquals(1,freedRedSlot);
        redRegistrationList = parkingLot.getRegistrationNumbersByColor(color1);
        assertFalse(redRegistrationList.isEmpty());
        assertEquals(1,redRegistrationList.size());


    }

    @Test
    public void statusTest() {
        try {
            fillUpParking();
            parkingLot.status();
        } catch (Exception e) {
            assertNull(e);
        }
    }



    private void fillUpParking() {

        Ticket ticket = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1,ticket.getSlot());

        Ticket ticket2= parkingLot.parkVehicle(reg2,color1);
        assertEquals(2,ticket2.getSlot());

        Ticket ticket3 = parkingLot.parkVehicle(reg3,color2);
        assertEquals(3,ticket3.getSlot());
    }




}
