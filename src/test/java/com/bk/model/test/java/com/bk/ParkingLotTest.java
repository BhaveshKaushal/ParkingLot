package test.java.com.bk.model.test.java.com.bk;

import main.java.com.bk.ParkingLot;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    @Test
    public void parkingLotTest() {
        int size = 10;
        ParkingLot parkinglot = new ParkingLot(size);
        assertNotNull(parkinglot);
    }
}
