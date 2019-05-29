package com.bk.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class VehicleTest {

    @Test
    public void vehicleTest() {
        String registrationNumber = "test-registration-number";
        String color= "white";
        Vehicle vehicle = new Vehicle(registrationNumber,color);

        assertNotNull(vehicle);
        assertEquals(color, vehicle.getColor());
        assertEquals(registrationNumber,vehicle.getRegistrationNumber());
    }
}
