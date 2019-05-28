package main.java.com.bk;

import main.java.com.bk.model.Slot;
import main.java.com.bk.model.Vehicle;

import java.util.*;

public class ParkingLot {

    private int size;

    private Map<String, Slot> reservedMap;

    private SortedSet<Slot> availableSlots;

    private List<Slot> slotList;

    public ParkingLot(int size) {
        init(size);
    }

    private void init(int size) {
        this.size = size;
        this.reservedMap =  new HashMap<>();
        this.slotList = new ArrayList<>(size);
        this.availableSlots = new TreeSet<>();
        for(int i = 0; i< size; i++) {
            Slot slot = new Slot(i+1);
            availableSlots.add(slot);
            slotList.add(slot);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SortedSet<Slot> getAvailableSlots() {
        return availableSlots;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public int parkVehicle(String registrationNumber, String color) {
        Vehicle vehicle  = new Vehicle(registrationNumber, color);
       Slot slot =  this.availableSlots.first();

       availableSlots.remove(slot);
       //System.out.println("Available Slots: "+availableSlots);
       slot.setVehicle(vehicle);

       this.reservedMap.put(registrationNumber,slot);

       this.slotList.add(slot.getSlotNumber(),slot);

       return slot.getSlotNumber();
    }

    public int getSlotNumberByRegistrationNumber(String registrationNumber) {
        Slot slot = this.reservedMap.get(registrationNumber);

        if(slot != null) {
           return slot.getSlotNumber();
        } else {
            return -1;
        }
    }
}
