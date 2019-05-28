package main.java.com.bk;

import main.java.com.bk.model.Slot;

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
            Slot slot = new Slot(i);
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
}
