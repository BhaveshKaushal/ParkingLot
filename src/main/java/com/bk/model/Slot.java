package com.bk.model;

import java.util.Objects;

public class Slot implements Comparable<Slot>{
   private int slotNumber;

    private Vehicle vehicle;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int compareTo(Slot o) {
        return (this.getSlotNumber() - o.getSlotNumber()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return slotNumber == slot.slotNumber &&
                Objects.equals(vehicle, slot.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, vehicle);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotNumber=" + slotNumber +
                ", vehicle=" + vehicle +
                '}';
    }
}
