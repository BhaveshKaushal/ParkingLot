package main.java.com.bk.model;

public class Slot {
   private int id;

    private Vehicle vehicle;

    public Slot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
