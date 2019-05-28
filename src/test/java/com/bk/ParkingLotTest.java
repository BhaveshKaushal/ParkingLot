package test.java.com.bk;

import main.java.com.bk.ParkingLot;
import main.java.com.bk.model.Slot;
import main.java.com.bk.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private int size = 3;
    private ParkingLot parkingLot;

    private String reg1 = "reg1";
    private String reg2 = "reg2";
    private String reg3 = "reg3";

    private String color1 = "red";
    private String color2 = "black";
    private String color3 = "white";

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


        int slotNumber = parkingLot.parkVehicle(reg1,color1);
       assertEquals(2,parkingLot.getNextFreeSlot());

        Slot reservedSlot = parkingLot.getSlotMap().get(slotNumber);
        assertNotNull(reservedSlot);
        assertEquals(slotNumber, reservedSlot.getSlotNumber());

        Vehicle vehicle = reservedSlot.getVehicle();
        assertNotNull(vehicle);
        assertEquals(color1, vehicle.getColor());
        assertEquals(reg1,vehicle.getRegistrationNumber());
        assertEquals(1,parkingLot.getSlotNumberByRegistrationNumber(reg1));

    }

    @Test
    public void parkingFullTest() {

        assertEquals(3,parkingLot.getSlotMap().size());
        int slotNumber = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1, slotNumber);
        assertEquals(2,parkingLot.getNextFreeSlot());
        assertEquals(3,parkingLot.getSlotMap().size());

        int slotNumber2 = parkingLot.parkVehicle(reg2,color2);
        assertEquals(2,slotNumber2);
        assertEquals(3,parkingLot.getNextFreeSlot());


        int slotNumber3 = parkingLot.parkVehicle(reg3,color3);
        assertEquals(3,slotNumber3);
        assertEquals(-1,parkingLot.getNextFreeSlot());

        int slotNumber4 = parkingLot.parkVehicle("parking_full","blue");
        assertEquals(-1,slotNumber4);

    }



    @Test
    public void freeSlotTest() {

        int slotNumber = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1,slotNumber);
        assertEquals(2, parkingLot.getNextFreeSlot());
        assertEquals(slotNumber,parkingLot.getSlotNumberByRegistrationNumber(reg1));
        assertEquals(reg1,parkingLot.getSlotMap().get(slotNumber).getVehicle().getRegistrationNumber());

        int slotNumber2 = parkingLot.parkVehicle(reg2,color2);
        assertEquals(2,slotNumber2);
        assertEquals(3, parkingLot.getNextFreeSlot());
        assertEquals(slotNumber2,parkingLot.getSlotNumberByRegistrationNumber(reg2));
        assertEquals(reg2,parkingLot.getSlotMap().get(slotNumber2).getVehicle().getRegistrationNumber());


        int slotNumber3 = parkingLot.parkVehicle(reg3,color3);
        assertEquals(3,slotNumber3);
        assertEquals(-1, parkingLot.getNextFreeSlot());
        assertEquals(slotNumber3,parkingLot.getSlotNumberByRegistrationNumber(reg3));
        assertEquals(reg3,parkingLot.getSlotMap().get(slotNumber3).getVehicle().getRegistrationNumber());

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

        intializeForColorTest();
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
        intializeForColorTest();
        List<String> redRegistrationList = parkingLot.getRegistrationListByColor(color1);
        assertNotNull(redRegistrationList);
        assertFalse(redRegistrationList.isEmpty());
        assertEquals(2, redRegistrationList.size());
        assertEquals(Arrays.asList(reg1,reg2),redRegistrationList);

        List<String> blackSlotNumbers = parkingLot.getRegistrationListByColor(color2);
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
        redRegistrationList = parkingLot.getRegistrationListByColor(color1);
        assertFalse(redRegistrationList.isEmpty());
        assertEquals(1,redRegistrationList.size());
        assertEquals(reg2,redRegistrationList.get(0));
    }

    private void intializeForColorTest() {

        int slotNumber1 = parkingLot.parkVehicle(reg1,color1);
        assertEquals(1,slotNumber1);

        int slotNumber2 = parkingLot.parkVehicle(reg2,color1);
        assertEquals(2,slotNumber2);

        int slotNumber3 = parkingLot.parkVehicle(reg3,color2);
        assertEquals(3,slotNumber3);
    }




}
