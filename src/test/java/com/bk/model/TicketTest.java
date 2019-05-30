package com.bk.model;

import com.bk.BaseTest;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketTest extends BaseTest {

    @Test
    public void ticketTest() {
        printStart();

        String registrationNumber = "test-registration-number";
        String color= "white";
        int slotNumber = 1;
        Ticket ticket = new Ticket(registrationNumber,color,1);

        assertNotNull(ticket);
        assertEquals(color, ticket.getColor());
        assertEquals(registrationNumber,ticket.getRegistrationNumber());
        assertEquals(slotNumber,ticket.getSlot());

        printEnd();
    }
}
