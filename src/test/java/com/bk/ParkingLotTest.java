package test.java.com.bk;

import main.java.com.bk.ParkingLot;
import main.java.com.bk.model.Slot;
import main.java.com.bk.model.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private int size = 3;
    private ParkingLot parkingLot;

    private void testSlotInitialization(List<Slot> slotList, int size) {

        for(int i = 0; i < size; i++) {
            Slot slot =  slotList.get(i);
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

        int slotNumber = parkingLot.parkVehicle("test_reg1","white");
        assertEquals(1, slotNumber);

        int slotNumber2 = parkingLot.parkVehicle("test_reg2","black");
        assertEquals(2,slotNumber2);


        int slotNumber3 = parkingLot.parkVehicle("test_reg3","blue");
        assertEquals(3,slotNumber3);

        int slotNumber4 = parkingLot.parkVehicle("parking_full","red");
        assertEquals(-1,slotNumber4);

    }



    @Test
    public void freeSlotTest() {

        String regNo1 = "reg1";
        String color1 =  "blue";
        int slotNumber = parkingLot.parkVehicle(regNo1,color1);
        assertEquals(1,slotNumber);
        assertEquals(2, parkingLot.getAvailableSlots().size());
        assertEquals(slotNumber,parkingLot.getSlotNumberByRegistrationNumber(regNo1));
        assertEquals(regNo1,parkingLot.getSlotList().get(slotNumber).getVehicle().getRegistrationNumber());

        String regNo2 = "reg2";
        String color2 = "red";
        int slotNumber2 = parkingLot.parkVehicle(regNo2,color2);
        assertEquals(2,slotNumber2);
        assertEquals(1, parkingLot.getAvailableSlots().size());
        assertEquals(slotNumber2,parkingLot.getSlotNumberByRegistrationNumber(regNo2));
        assertEquals(regNo2,parkingLot.getSlotList().get(slotNumber2).getVehicle().getRegistrationNumber());

        String regNo3 = "reg3";
        String color3 = "red";
        int slotNumber3 = parkingLot.parkVehicle(regNo3,color3);
        assertEquals(3,slotNumber3);
        assertEquals(0, parkingLot.getAvailableSlots().size());
        assertEquals(slotNumber3,parkingLot.getSlotNumberByRegistrationNumber(regNo3));
        assertEquals(regNo2,parkingLot.getSlotList().get(slotNumber2).getVehicle().getRegistrationNumber());

        int freedSlotNumber = parkingLot.freeSlot(1);
        assertNotEquals(-1,freedSlotNumber);
        assertEquals(1,parkingLot.getAvailableSlots().size());
        assertEquals(-1, parkingLot.getSlotNumberByRegistrationNumber(regNo1));
        assertNull(parkingLot.getSlotList().get(freedSlotNumber).getVehicle());
        assertEquals(1,parkingLot.getAvailableSlots().first().getSlotNumber());
    }

    @Test
    public void getSlotListbyColorTest(){
        String regNo1= "regNo1";
        String color1 = "red";
        int slotNumber1 = parkingLot.parkVehicle(regNo1,color1);

        String regNo2= "regNo2";
        int slotNumber2 = parkingLot.parkVehicle(regNo2,color1);

        String regNo3= "regNo3";
        String color2 = "black";
        int slotNumber3 = parkingLot.parkVehicle(regNo3,color2);

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


    }


}
