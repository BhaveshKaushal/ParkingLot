package main.java.com.bk.model;

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
        return (this.getSlotNumber() > o.getSlotNumber() ? this.getSlotNumber() : o.getSlotNumber()) ;
    }

}
